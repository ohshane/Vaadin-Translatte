package com.vaadin.upload;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

public class FileUploadBlock extends VerticalLayout {
    private FileUploader fileUploader;

    public FileUploadBlock() {
        fileUploader = new FileUploader();

        this.addComponent(fileUploader);
        this.addStyleNames(ValoTheme.BUTTON_PRIMARY);
        this.setComponentAlignment(fileUploader, Alignment.TOP_RIGHT);
    }
}