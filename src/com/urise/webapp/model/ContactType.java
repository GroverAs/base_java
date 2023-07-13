package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    LINKEDIN("Профиль Linkedin: "),
    GITHUB("Профиль Github: "),
    STACKOVERFLOW("Профиль Stackoverflow: ");


    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}