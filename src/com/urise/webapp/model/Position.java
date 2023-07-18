package com.urise.webapp.model;

import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Position implements Serializable {

    private String title;

    private String description;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate startDate;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate endDate;

    public Position() {
    }

    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.description = description == null ? "" : description;
            this.startDate = startDate;
            this.endDate = endDate;
        }



        public String getTitle () {
            return title;
        }

        public String getDescription () {
            return description;
        }

        public LocalDate getStartDate () {
            return startDate;
        }

        public LocalDate getEndDate () {
            return endDate;
        }

        @Override
        public boolean equals (Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Position period = (Position) o;

            if (!title.equals(period.title)) return false;
            if (!Objects.equals(description, period.description)) return false;
            if (!startDate.equals(period.startDate)) return false;
            return endDate.equals(period.endDate);
        }

        @Override
        public int hashCode () {
            int result = title.hashCode();
            result = 31 * result + (description != null ? description.hashCode() : 0);
            result = 31 * result + startDate.hashCode();
            result = 31 * result + endDate.hashCode();
            return result;
        }

        @Override
        public String toString () {
            return "Period{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }
    }
