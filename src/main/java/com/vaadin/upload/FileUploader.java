package com.vaadin.upload;

import com.vaadin.DBConnect;
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
    private LangChooseBlock langChooseBlock;
    private String lang1 = "";
    private String lang2 = "";
    private FileOutputStream fos;

    public FileUploader(LangChooseBlock langChooseBlock) {
        this.langChooseBlock = langChooseBlock;
        this.setSpacing(true);
        this.setMargin(false);

        upload = new Upload("place your *.srt file here", this);
        upload.setVisible(false);

        this.langChooseBlock.getSource_lang().addValueChangeListener(event -> {
            lang1 = event.getValue().getLanguageCode();
            if (!lang1.equals("") && !lang2.equals("")) {
                System.out.printf("languageCode : (%s, %s)\n", lang1, lang2);
                upload.setVisible(true);
            }
        });

        this.langChooseBlock.getTarget_lang().addValueChangeListener(event -> {
            lang2 = event.getValue().getLanguageCode();
            if (!lang1.equals("") && !lang2.equals("")) {
                System.out.printf("languageCode : (%s, %s)\n", lang1, lang2);
                upload.setVisible(true);
            }
        });


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
        fos = null;
        this.fileName = fileName;

        upload.setVisible(false);
        progressBar.setVisible(true);

        String[] fileNameArray = fileName.split("\\.");
        String fileExtension = fileNameArray[fileNameArray.length - 1];

        if (! fileExtension.equals("srt")) {
            new Notification("Check file extension (only *.srt is allowed)",
                    Notification.Type.WARNING_MESSAGE)
                    .show(Page.getCurrent());
            upload.setVisible(true);
            progressBar.setVisible(false);

            this.fileName = "";
            return null;
        }

        // Create upload stream
        // Stream to write to - fos
        try {
            // Open the file for writing.
            file = new File("tmp/" + fileName);
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            new Notification("Upload unsuccessful",
                    e.getMessage(),
                    Notification.Type.ERROR_MESSAGE)
                    .show(Page.getCurrent());

            upload.setVisible(true);
            progressBar.setVisible(false);

            return null;
        }
        System.out.println(fos.toString());
        return fos; // Return the output stream to write to
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        DBConnect db = new DBConnect("sourcefilegeneral");
        db.addSourceData(fileName, lang1, lang2, fos.toString());

        upload.setVisible(true);
        progressBar.setVisible(false);
        Label fileLabel = new Label(String.format("%s │ %d KB", fileName, file.length()/1024));
        fileLabel.addStyleNames(ValoTheme.LABEL_TINY, ValoTheme.LABEL_SUCCESS);
        this.addComponent(fileLabel);
        new Notification("Upload completed",
                null,
                Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());

        this.fileName = "";
    }

}
