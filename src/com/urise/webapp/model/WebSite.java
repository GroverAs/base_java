package com.urise.webapp.model;

import java.util.Objects;

public class WebSite {

    private final String name;

    private final String url;

    public WebSite(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WebSite webSite = (WebSite) o;

        if (!name.equals(webSite.name)) return false;
        return Objects.equals(url, webSite.url);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WebSite{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
