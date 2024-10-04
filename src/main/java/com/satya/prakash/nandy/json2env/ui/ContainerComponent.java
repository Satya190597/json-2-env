package com.satya.prakash.nandy.json2env.ui;

import com.intellij.openapi.ui.VerticalFlowLayout;

import javax.swing.*;

public class ContainerComponent implements JsonToEnvComponent {
    @Override
    public JComponent getComponent() {
        return new JPanel(new VerticalFlowLayout());
    }
}
