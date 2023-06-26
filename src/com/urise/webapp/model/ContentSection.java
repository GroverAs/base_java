package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ContentSection extends AbstractSection{

    private final List<String> elements;

    public ContentSection(List<String> elements) {
        this.elements = elements;
    }

    public List<String> getElements() {
        return elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContentSection that = (ContentSection) o;

        return elements.equals(that.elements);
    }

    @Override
    public int hashCode() {
        return elements.hashCode();
    }

    @Override
    public String toString() {
        return "ContentSection{" +
                "elements=" + elements +
                '}';
    }
}
