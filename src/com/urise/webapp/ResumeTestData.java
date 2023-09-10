package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;



public class ResumeTestData {
    public static Resume createResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);

        resume.addContact(ContactType.PHONE_NUMBER, "111-22-33");
        resume.addContact(ContactType.EMAIL, "rookie@ya.ru");
        resume.addContact(ContactType.SKYPE, "xyz");
        resume.addContact(ContactType.GITHUB, "GitHub");
        resume.addContact(ContactType.LINKEDIN, "https://www.linkedin.com/in/qwerty");
        resume.addContact(ContactType.STACKOVERFLOW,"StackOverflow");

        resume.addSection(SectionType.PERSONAL, new TextSection("Personal information"));
        resume.addSection(SectionType.OBJECTIVE, new TextSection("Java developer"));
        resume.addSection(SectionType.ACHIEVEMENT, new ContentSection("Successful experience in Java",
                "Разработка Web приложений", "Организация онлайн стажировок и ведение проектов"));
        resume.addSection(SectionType.QUALIFICATIONS, new ContentSection("Java", "JavaScript", "Tomcat"));

        resume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Высокие технологии", "http://Высокие технологии.ru",
                                new Position(
                                        LocalDate.of(2004, Month.FEBRUARY, 1),
                                        LocalDate.of(2005, Month.DECEMBER, 1),
                                        "Junior developer", "just start working"),
                                new Position(
                                        LocalDate.of(2005, Month.APRIL, 1),
                                        LocalDate.of(2007, Month.JULY, 1),
                                        "Middle developer", "all is good"))));
        resume.addSection(SectionType.EXPERIENCE,
                new CompanySection(
                        new Company("Прорыв", "http://Прорыв.ru",
                                new Position(
                                        LocalDate.of(2017, Month.OCTOBER, 1),
                                        LocalDate.now(),
                                        "Senior developer", "Fantastic - bombastic"))));
        resume.addSection(SectionType.EDUCATION,
                new CompanySection(
                        new Company("University", null,
                                new Position(
                                        LocalDate.of(1996, Month.FEBRUARY, 1),
                                        LocalDate.of(2000, Month.DECEMBER, 1),
                                        "student", "IT faculty"),
                                new Position(
                                        LocalDate.of(2001, Month.SEPTEMBER, 1),
                                        LocalDate.of(2003, Month.JUNE, 1),
                                        "aspirant", null))));
        return resume;
    }
}
