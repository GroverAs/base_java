package com.urise.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ContentSection extends Section{
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> elements;

    public ContentSection() {
    }

    public ContentSection(String... elements) {
        this(Arrays.asList(elements));
    }

    public ContentSection(List<String> elements) {
        Objects.requireNonNull(elements, "items must not be null");
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
        return elements.toString();
    }
}
