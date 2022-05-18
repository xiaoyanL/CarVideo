package com.fd.baselibrary.base;

import java.util.Locale;

public class LanguageBean {
    private Locale language;
    private String name;

    public LanguageBean(Locale language, String name) {
        this.language = language;
        this.name = name;
    }
    public LanguageBean(Locale language) {
        this.language = language;
    }

    public Locale getLanguage() {
        return language;
    }

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
