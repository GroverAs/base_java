package com.urise.webapp.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            writeWithException(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            Map<SectionType, Section> sections = resume.getSections();
            writeWithException(dos, sections.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                writeSection(entry, dos);
            });

        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readWithException(dis, () -> readSection(resume, dis));

            return resume;
        }
    }


    private void writeSection(Map.Entry<SectionType, Section> entry, DataOutputStream dos) throws IOException {
        SectionType sectionType = entry.getKey();
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> writeTextSection(dos, (TextSection) entry.getValue());
            case ACHIEVEMENT, QUALIFICATIONS -> writeContentSection(dos, (ContentSection) entry.getValue());
            case EXPERIENCE, EDUCATION -> writeCompanySection(dos, (CompanySection) entry.getValue());
        }
    }

    private void writeTextSection(DataOutputStream dos, TextSection textSection) throws IOException {
        dos.writeUTF(textSection.getContent());
    }

    private void writeContentSection(DataOutputStream dos, ContentSection contentSection) throws IOException {
        writeWithException(dos, contentSection.getElements(), dos::writeUTF);
    }

    private void writeCompanySection(DataOutputStream dos, CompanySection companySection) throws IOException {
        writeWithException(dos, companySection.getCompanies(), company -> {
            dos.writeUTF(company.getHomePage().getName());
            dos.writeUTF(company.getHomePage().getUrl());

            writeWithException(dos, company.getPositions(), position -> {
                writeLocalDate(dos, position.getStartDate());
                writeLocalDate(dos, position.getEndDate());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());

            });
        });
    }


    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }


    private void readSection(Resume resume, DataInputStream dis) throws IOException {
        SectionType sectionType = SectionType.valueOf(dis.readUTF());
        switch (sectionType) {
            case PERSONAL, OBJECTIVE -> addTextSection(resume, dis, sectionType);
            case ACHIEVEMENT, QUALIFICATIONS -> addContentSection(resume, dis, sectionType);
            case EXPERIENCE, EDUCATION -> addCompanySection(resume, dis, sectionType);
            default -> throw new IllegalStateException("Unexpected value: " + sectionType);
        }
    }


    private void addTextSection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        resume.addSection(sectionType, new TextSection(dis.readUTF()));
    }

    private void addContentSection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        resume.addSection(sectionType, new ContentSection(readList(dis, dis::readUTF)));
    }

    private void addCompanySection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        resume.addSection(sectionType, new CompanySection(readList(dis, () ->
                new Company(dis.readUTF(), dis.readUTF(), (Position) readList(dis, () ->
                        new Position(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()))))));
    }


    private interface collectionWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, collectionWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T collections : collection) {
            writer.write(collections);
        }
    }

    private <T> void readWithException(DataInputStream dis, collectionReader<T> reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private interface collectionReader<T> {
        void read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> elementReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(elementReader.readElement());
        }
        return list;
    }

    private interface ElementReader<T> {
        T readElement() throws IOException;
    }


}


