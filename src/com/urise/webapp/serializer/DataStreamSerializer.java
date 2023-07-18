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
            if (size == 0) {
                return resume;
            }
            for (int i = 0; i < size; i++) {
                addSection(resume, dis);
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
        dos.writeUTF("TextSection");
        dos.writeUTF(textSection.getContent());
    }

    private void writeContentSection(DataOutputStream dos, ContentSection contentSection) throws IOException {
        dos.writeUTF("ContentSection");
        List<String> elements = contentSection.getElements();
        dos.writeInt(elements.size());
        for (String s : elements) {
            dos.writeUTF(s);
        }
    }

    private void writeCompanySection(DataOutputStream dos, CompanySection companySection) throws IOException {
        dos.writeUTF("CompanySection");
        List<Company> companies = companySection.getCompanies();
        dos.writeInt(companies.size());
        for (Company company : companies) {
            dos.writeUTF(company.getName());
            dos.writeUTF(company.getWebSite());
            List<Position> positions = company.getPositions();
            for (Position position : positions) {
                dos.writeUTF(position.getStartDate().toString());
                dos.writeUTF(position.getEndDate().toString());
                dos.writeUTF(position.getTitle());
                String description = position.getDescription();
            }
        }
    }

    private void addSection(Resume resume, DataInputStream dis) throws IOException {
        SectionType sectionType = SectionType.valueOf(dis.readUTF());
        String section = dis.readUTF();
        switch (section) {
            case "TextSection" -> addTextSection(resume, dis, sectionType);
            case "ContentSection" -> addContentSection(resume, dis, sectionType);
            case "CompanySection" -> addCompanySection(resume, dis, sectionType);
        }
    }


    private void addTextSection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        resume.addSection(sectionType, new TextSection(dis.readUTF()));
    }

    private void addContentSection(Resume r, DataInputStream dis, SectionType sectionType) throws IOException {
        List<String> items = new ArrayList<>();
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            items.add(dis.readUTF());
        }
        r.addSection(sectionType, new CompanySection((Company) items));
    }

    private void addCompanySection(Resume resume, DataInputStream dis, SectionType sectionType) throws IOException {
        int size = dis.readInt();
        List<Company> companies = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            String name = dis.readUTF();
            String webSite = dis.readUTF();
            if (webSite.equals("")) webSite = null;
            int posSize = dis.readInt();
            List<Position> positions = new ArrayList<>();
            for (int j = 0; j < posSize; j++) {
                LocalDate startDate = LocalDate.parse(dis.readUTF());
                LocalDate endDate = LocalDate.parse(dis.readUTF());
                String title = dis.readUTF();
                String description = dis.readUTF();
                if (description.equals("")) description = null;
                positions.add(new Position(startDate, endDate, title, description));
            }
            resume.addSection(sectionType, new CompanySection());
        }
    }
}


