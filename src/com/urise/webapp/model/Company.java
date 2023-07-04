package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class Company {

    private final String name;

    private final String webSite;

    private final List<Position> positions;

    public Company(String name, String webSite, List<Position> positions) {
        this.name = name;
        this.webSite = webSite;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public String getWebSite() {
        return webSite;
    }

    public List<Position> getPeriods() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        if (!name.equals(company.name)) return false;
        if (!Objects.equals(webSite, company.webSite)) return false;
        return positions.equals(company.positions);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (webSite != null ? webSite.hashCode() : 0);
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", webSite='" + webSite + '\'' +
                ", positions=" + positions +
                '}';
    }
}
