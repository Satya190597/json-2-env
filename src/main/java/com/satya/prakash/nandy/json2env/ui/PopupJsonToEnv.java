package com.satya.prakash.nandy.json2env.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.popup.IconButton;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;

import javax.swing.*;
import java.awt.*;

public class PopupJsonToEnv {
    private final JComponent childComponent;
    private final JComponent highlightedComponent;
    public PopupJsonToEnv(JComponent childComponent, JComponent highlightedComponent) {
        this.childComponent = childComponent;
        this.highlightedComponent = highlightedComponent;
    }
    public JBPopup getComponent() {
        return JBPopupFactory.getInstance()
                .createComponentPopupBuilder(childComponent, highlightedComponent)
                .setTitle("Json 2 Env")
                .setMovable(true)
                .setResizable(false)
                .setRequestFocus(true)
                .setCancelOnWindowDeactivation(false)
                .setCancelOnClickOutside(false)
                .setCancelButton(new IconButton("Close", AllIcons.Actions.Close))
                .setMinSize(new Dimension(500,500))
                .setDimensionServiceKey(null,"JsonToEnvPopup",true)
                .createPopup();
    }
}
