package com.vaadin.upload;

public class Lang {
    private String languageName;
    private String languageCode;

    public Lang(String languageName, String languageCode) {
        this.languageName = languageName;
        this.languageCode = languageCode;
    }

    public Lang(String[] strings) {
        this.languageName = strings[0];
        this.languageCode = strings[1];
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

}
