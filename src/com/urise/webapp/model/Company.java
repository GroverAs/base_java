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

    private Link homePage;
    private String name;

//    private String webSite;

    private List<Position> positions = new ArrayList<>();

    public Company() {
    }

    public Company(String name, String url, Position... positions) {
        this(new Link(name, url), Arrays.asList(positions));
    }

    public Company(Link homePage, List<Position> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "Company{" +
                "homePage=" + homePage +
                ", positions=" + positions +
                '}';
    }

    //    public Company(String name, String webSite, Position... positions) {
//        this.name = name;
//        this.webSite = webSite == null ? "" : webSite;
//        this.positions = Arrays.asList(positions);
//    }
//
//    public Company(String name, List<Position> positions){
//        this.name = name;
//        this.positions = positions;
//    }
//
//    public Company(String name, String webSite, List<Position> positions) {
//        this.name=name;
//        this.webSite = webSite == null ? "" : webSite;
//        this.positions = positions;
//    }

//    public String getName() {
//        return name;
//    }
//
//    public String getWebSite() {
//        return webSite;
//    }


}
