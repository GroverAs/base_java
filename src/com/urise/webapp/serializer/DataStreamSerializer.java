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
        List<String> elements = contentSection.getElements();
        writeWithException(dos, elements, str -> dos.writeUTF(str));
    }

    private void writeCompanySection(DataOutputStream dos, CompanySection companySection) throws IOException {
        List<Company> companies = companySection.getCompanies();
        writeWithException(dos, companies, company -> {
            dos.writeUTF(company.getName());
            dos.writeUTF(company.getWebSite());

            List<Position> positions = company.getPositions();
            writeWithException(dos, positions, position -> {
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
        List<String> items = new ArrayList<>();
        readWithException(dis, ()-> items.add(dis.readUTF()));
        resume.addSection(sectionType, new ContentSection(items));
    }

    private void addCompanySection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        List<Company> companies = new ArrayList<>();
        readWithException(dis, () -> {
            String name = dis.readUTF();
            String webSite = dis.readUTF();

            List<Position> positions = new ArrayList<>();
            readWithException(dis, () -> {
                LocalDate startDate = readLocalDate(dis);
                LocalDate endDate = readLocalDate(dis);
                String title = dis.readUTF();
                String description = dis.readUTF();

                positions.add(new Position(startDate, endDate, title, description));
            });
            companies.add(new Company(name, webSite, positions));
        });
        resume.addSection(sectionType, new CompanySection(companies));

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

    private interface collectionReader<T> {
        void read() throws IOException;
    }

    private <T> void readWithException(DataInputStream dis, collectionReader<T> reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

}


