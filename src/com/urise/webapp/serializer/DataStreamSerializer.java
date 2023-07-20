package com.urise.webapp.serializer;

import com.urise.webapp.model.*;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class DataStreamSerializer implements Serializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                writeSection(entry, dos);

            }
        }
    }


    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                readSection(resume, dis);
            }
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
        dos.writeInt(elements.size());
        for (String s : elements) {
            dos.writeUTF(s);
        }
    }

    private void writeCompanySection(DataOutputStream dos, CompanySection companySection) throws IOException {
        List<Company> companies = companySection.getCompanies();
        dos.writeInt(companies.size());
        for (Company company : companies) {
            dos.writeUTF(company.getName());
            dos.writeUTF(company.getWebSite());
            List<Position> positions = company.getPositions();
            dos.writeInt(positions.size());
            for (Position position : positions) {
                writeLocalDate(dos, position.getStartDate());
                writeLocalDate(dos, position.getEndDate());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getDescription());
            }
        }
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
        int size = dis.readInt();
        List<String> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
        resume.addSection(sectionType, new ContentSection(items));
    }

    private void addCompanySection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        List<Company> companies = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            String name = dis.readUTF();
            String webSite = dis.readUTF();
            List<Position> positions = new ArrayList<>();
            int posSize = dis.readInt();
            for (int j = 0; j < posSize; j++) {
                LocalDate startDate = readLocalDate(dis);
                LocalDate endDate = readLocalDate(dis);
                String title = dis.readUTF();
                String description = dis.readUTF();
                positions.add(new Position(startDate, endDate, title, description));
            }
            companies.add(new Company(name, webSite, positions));
        }
        resume.addSection(sectionType, new CompanySection(companies));
    }
}


