package com.satya.prakash.nandy.json2env.ui;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.ui.popup.*;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class PopupComponent {
    private final JComponent childComponent;
    private final JComponent highlightedComponent;
    public PopupComponent(JComponent childComponent, JComponent highlightedComponent) {
        this.childComponent = childComponent;
        this.highlightedComponent = highlightedComponent;
    }
    public JBPopup getComponent() {
        JBPopup popup = JBPopupFactory.getInstance()
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
        setCloseHandler(popup);
        return popup;
    }
    private void setCloseHandler(JBPopup popup) {
        popup.addListener(new JBPopupListener() {
            @Override
            public void onClosed(@NotNull LightweightWindowEvent event) {
                JsonToEnvRepo.getRepo().reset();
            }
        });
    }
}
