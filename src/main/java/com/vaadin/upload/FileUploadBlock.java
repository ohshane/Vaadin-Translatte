package com.vaadin.upload;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class FileUploadBlock extends VerticalLayout {
    private FileUploader fileUploader;
    private LangChooseBlock langChooseBlock;

    public FileUploadBlock(LangChooseBlock langChooseBlock) {
        this.langChooseBlock = langChooseBlock;

        fileUploader = new FileUploader(langChooseBlock);

        this.addComponent(fileUploader);
        this.addStyleNames(ValoTheme.BUTTON_PRIMARY);
        this.setComponentAlignment(fileUploader, Alignment.TOP_RIGHT);
    }
}