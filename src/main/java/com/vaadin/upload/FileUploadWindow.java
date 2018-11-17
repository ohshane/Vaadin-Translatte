package com.vaadin.upload;

import com.vaadin.ui.*;

public class FileUploadWindow extends Window {
    public FileUploadWindow() {
        super("File uploader");
        this.center();

        this.setDraggable(true);
        this.setResizable(false);
        this.setModal(true);

        final VerticalLayout subContent = new VerticalLayout();
        subContent.addComponent(new LangChooseBlock());

        final FileUploadBlock fileUploadBlock = new FileUploadBlock();

        subContent.addComponent(fileUploadBlock);
        subContent.setComponentAlignment(fileUploadBlock, Alignment.BOTTOM_RIGHT);

        this.setContent(subContent);
    }
}
