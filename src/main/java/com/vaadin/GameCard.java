package com.vaadin;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

@HtmlImport("html/test.html")
@JavaScript("GameCard.js")
public class GameCard extends AbstractJavaScriptComponent {
    public GameCard(String symbol, String rank) {

    }

}