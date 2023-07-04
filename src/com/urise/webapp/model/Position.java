package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.Objects;


public class Position {

    private final String title;

    private final String description;

    private final LocalDate startDate;

    private final LocalDate endDate;


    public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.title = title;
            this.description = description;
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
