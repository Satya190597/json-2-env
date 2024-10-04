package com.satya.prakash.nandy.json2env;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.popup.JBPopup;

import com.satya.prakash.nandy.json2env.ui.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class JsonToEnvApplication extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        JComponent component = new UiBuilder().buildUi();
        JBPopup popup = new PopupJsonToEnv(component,component).getComponent();
        popup.showInFocusCenter();
    }
}
