package com.vaadin;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.upload.FileUploadWindow;

import javax.servlet.annotation.WebServlet;
import javax.swing.text.html.HTML;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final Button fileUploaderButton = new Button("File uploader", clickEvent -> {
            final FileUploadWindow fileUploadWindow = new FileUploadWindow();
            UI.getCurrent().addWindow(fileUploadWindow);
        });
        fileUploaderButton.setIcon(FontAwesome.UPLOAD);
        fileUploaderButton.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.BUTTON_HUGE, ValoTheme.BUTTON_ICON_ALIGN_TOP);
        layout.addComponent(fileUploaderButton);
        setContent(fileUploaderButton);

        final Button fileExplorerButton = new Button("File explorer", clickEvent -> {
            final FileUploadWindow fileUploadWindow = new FileUploadWindow();
            UI.getCurrent().addWindow(fileUploadWindow);
        });
        fileUploaderButton.setIcon(FontAwesome.UPLOAD);
        fileUploaderButton.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.BUTTON_HUGE, ValoTheme.BUTTON_ICON_ALIGN_TOP);
        layout.addComponent(fileUploaderButton);
        setContent(fileUploaderButton);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
