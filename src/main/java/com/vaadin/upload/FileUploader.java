package com.vaadin.upload;

import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class FileUploader extends VerticalLayout implements Upload.Receiver, Upload.SucceededListener {
    private File file;
    private Upload upload;
    private ProgressBar progressBar;
    private String fileName = "";

    public FileUploader() {
        this.setSpacing(true);
        this.setMargin(false);

        upload = new Upload(null, this);
        upload.setVisible(true);
        upload.addSucceededListener(this);
        this.addComponent(upload);

        progressBar = new ProgressBar();
        progressBar.setVisible(false);
        progressBar.setIndeterminate(true);
        progressBar.setCaption("Uploading...");
        this.addComponent(progressBar);
    }

    @Override
    public OutputStream receiveUpload(String fileName, String mimeType) {
        this.fileName = fileName;

        upload.setVisible(false);
        progressBar.setVisible(true);
        // Create upload stream
        FileOutputStream fos; // Stream to write to
        try {
            // Open the file for writing.
            // file = new File("/tmp/uploads/" + fileName);
            file = new File("./tmp/" + fileName);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Could not open file",
                    e.getMessage(),
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());

            upload.setVisible(true);
            progressBar.setVisible(false);

            Label fileLabel = new Label(fileName);
            fileLabel.addStyleNames(ValoTheme.LABEL_FAILURE);
            this.addComponent(fileLabel);

            this.fileName = "";
            return null;
        }
        return fos; // Return the output stream to write to
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        upload.setVisible(true);
        progressBar.setVisible(false);
        Label fileLabel = new Label(fileName);
        fileLabel.addStyleNames(ValoTheme.LABEL_SUCCESS);
        this.addComponent(fileLabel);
        new Notification("Upload completed",
                null,
                Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());
    }

}
