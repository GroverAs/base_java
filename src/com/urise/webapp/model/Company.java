package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public static final Company EMPTY = new Company("","", Position.EMPTY);

    private String name;

    private String webSite;

    private List<Position> positions = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String webSite, Position... positions) {
        this.name = name;
        this.webSite = webSite == null ? "" : webSite;
        this.positions = Arrays.asList(positions);
    }

    public Company(String name, List<Position> positions){
        this.name = name;
        this.positions = positions;
    }

    public Company(String name, String webSite, List<Position> positions) {
        this.name=name;
        this.webSite = webSite == null ? "" : webSite;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public String getWebSite() {
        return webSite;
    }

    public List<Position> getPositions() {
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
