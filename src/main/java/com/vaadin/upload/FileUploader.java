package com.vaadin.upload;

import com.vaadin.DBConnect;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.*;
import java.util.Scanner;

public class FileUploader extends VerticalLayout implements Upload.Receiver, Upload.SucceededListener {
    private File file;
    private Upload upload;
    private ProgressBar progressBar;
    private String fileName = "";
    private LangChooseBlock langChooseBlock;
    private String lang1 = "";
    private String lang2 = "";

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
        FileOutputStream fos;
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

        return fos; // Return the output stream to write to
    }

    @Override
    public void uploadSucceeded(Upload.SucceededEvent succeededEvent) {
        FileReader fileReader;
        StringBuffer sb = new StringBuffer();

        try {
            fileReader = new FileReader(file);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DBConnect db = new DBConnect("sourcefilegeneral");
        db.addSourceData(fileName, lang1, lang2, sb.toString());

        upload.setVisible(true);
        progressBar.setVisible(false);
        Label fileLabel = new Label(String.format("%s │ %d KB", fileName, file.length()/1024));
        fileLabel.addStyleNames(ValoTheme.LABEL_TINY, ValoTheme.LABEL_SUCCESS);
        this.addComponent(fileLabel);
        new Notification("Upload completed",
                null,
                Notification.Type.HUMANIZED_MESSAGE).show(Page.getCurrent());

        this.fileName = "";
        System.out.println(sb.toString());
        sb.setLength(0);
    }

}
