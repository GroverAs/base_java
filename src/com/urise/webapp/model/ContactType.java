package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон: "),
    SKYPE("Skype: ") {
        @Override
        public String toHtml0(String value) {
            return "<a href='skype:" + value + "'>" + value + "</a>";
        }
    },
    EMAIL("Почта: ") {
        @Override
        public String toHtml0(String value) {
            return "<a href='mailto:" + value + "'>" + value + "</a>";
        }
    },
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

    protected String toHtml0(String value) {
        return title + value;
    }

    public String toHtml(String value) {
        return (value == null) ? "" : toHtml0(value);
    }
}