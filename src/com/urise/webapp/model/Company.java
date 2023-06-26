package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Company {

    public final String title;

    public final String description;

    public final LocalDate startDate;

    public final LocalDate endDate;

    public Company(String title, String description, List<Period> periods, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!title.equals(company.title)) return false;
        if (!description.equals(company.description)) return false;
        if (!startDate.equals(company.startDate)) return false;
        return endDate.equals(company.endDate);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }
}
