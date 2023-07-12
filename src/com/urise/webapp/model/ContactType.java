package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон: "),
    SKYPE("Skype: "),
    EMAIL("Почта: "),
    LINKEDIN("Профиль Linkedin: "),
    GITHUB("Профиль Github: "),
    STACKOVERFLOW("Профиль Stackoverflow: ");


    private String title;

    ContactType() {
    }

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}