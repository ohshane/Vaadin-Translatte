package com.vaadin.upload;

import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class LangChooseBlock extends VerticalLayout {
    public LangChooseBlock() {
        setSizeFull();
        Component langChooseForm = langChooseForm();
        this.addComponent(langChooseForm);

        setComponentAlignment(langChooseForm, Alignment.MIDDLE_CENTER);
    }

    private Component langChooseForm() {
        final HorizontalLayout langChoosePanel = new HorizontalLayout();

        langChoosePanel.setSizeUndefined();
        langChoosePanel.setSpacing(true);

        List<Lang> langs = lang2List();
        ComboBox<Lang> source_lang = new ComboBox<>("Source language", langs);
        source_lang.setItemCaptionGenerator(Lang::getLanguageName);
        source_lang.setEmptySelectionAllowed(false);
        source_lang.addValueChangeListener(event -> {
            Notification.show("Language code: " +
                String.valueOf(event.getValue().getLanguageCode()));
        });
        langChoosePanel.addComponent(source_lang);

        ComboBox<Lang> target_lang = new ComboBox<>("Target language", langs);
        target_lang.setItemCaptionGenerator(Lang::getLanguageName);
        target_lang.setEmptySelectionAllowed(false);
        target_lang.addValueChangeListener(event -> {
            Notification.show("Language code: " +
                String.valueOf(event.getValue().getLanguageCode()));
        });
        langChoosePanel.addComponent(target_lang);

        return langChoosePanel;
    }

    private List<Lang> lang2List() {
        String[][] langSet =
                {
                        {"Afrikaans", "af"},
                        {"Albanian", "sq"},
                        {"Arabic", "ar"},
                        {"Azerbaijani", "az"},
                        {"Basque", "eu"},
                        {"Belarusian", "be"},
                        {"Bengali", "bn"},
                        {"Bulgarian", "bg"},
                        {"Catalan", "ca"},
                        {"Chinese Simplified", "zh-CN"},
                        {"Chinese Traditional", "zh-TW"},
                        {"Croatian", "hr"},
                        {"Czech", "cs"},
                        {"Danish", "da"},
                        {"Dutch", "nl"},
                        {"English", "en"},
                        {"Esperanto", "eo"},
                        {"Estonian", "et"},
                        {"Filipino", "tl"},
                        {"Finnish", "fi"},
                        {"French", "fr"},
                        {"Galician", "gl"},
                        {"Georgian", "ka"},
                        {"German", "de"},
                        {"Greek", "el"},
                        {"Gujarati", "gu"},
                        {"Haitian Creole", "ht"},
                        {"Hebrew", "iw"},
                        {"Hindi", "hi"},
                        {"Hungarian", "hu"},
                        {"Icelandic", "is"},
                        {"Indonesian", "id"},
                        {"Irish", "ga"},
                        {"Italian", "it"},
                        {"Japanese", "ja"},
                        {"Kannada", "kn"},
                        {"Korean", "ko"},
                        {"Latin", "la"},
                        {"Latvian", "lv"},
                        {"Lithuanian", "lt"},
                        {"Macedonian", "mk"},
                        {"Malay", "ms"},
                        {"Maltese", "mt"},
                        {"Norwegian", "no"},
                        {"Persian", "fa"},
                        {"Polish", "pl"},
                        {"Portuguese", "pt"},
                        {"Romanian", "ro"},
                        {"Russian", "ru"},
                        {"Serbian", "sr"},
                        {"Slovak", "sk"},
                        {"Slovenian", "sl"},
                        {"Spanish", "es"},
                        {"Swahili", "sw"},
                        {"Swedish", "sv"},
                        {"Tamil", "ta"},
                        {"Telugu", "te"},
                        {"Thai", "th"},
                        {"Turkish", "tr"},
                        {"Ukrainian", "uk"},
                        {"Urdu", "ur"},
                        {"Vietnamese", "vi"},
                        {"Welsh", "cy"},
                        {"Yiddish", "yi"}
                };

        List<Lang> langs = new ArrayList<>();
        for (String[] strings : langSet) {
            langs.add(new Lang(strings));
        }

        return langs;
    }

}
