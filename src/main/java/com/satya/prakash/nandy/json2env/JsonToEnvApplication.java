package com.satya.prakash.nandy.json2env;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopup;

import com.intellij.openapi.ui.popup.JBPopupListener;
import com.intellij.openapi.ui.popup.LightweightWindowEvent;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;
import com.satya.prakash.nandy.json2env.ui.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Objects;

public class JsonToEnvApplication extends AnAction {
    private JBPopup popup;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        performCleanUpOnStart();
        JComponent component = new UiBuilder().buildUi();
        popup = new PopupComponent(component,component).getComponent();
        popup.showInFocusCenter();
    }

    private void performCleanUpOnStart() {
        closeExistingPopup();
        JsonToEnvRepo.getRepo().reset();
    }

    private void closeExistingPopup() {
        if(Objects.nonNull(popup) && popup.isVisible())
            popup.cancel();
    }
}
