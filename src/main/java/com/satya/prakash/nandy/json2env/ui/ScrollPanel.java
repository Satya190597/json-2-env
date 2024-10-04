package com.satya.prakash.nandy.json2env.ui;

import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class ScrollPanel implements JsonToEnvComponent {
    private final JComponent childComponent;
    ScrollPanel(JComponent childComponent) {
        this.childComponent = childComponent;
    }
    @Override
    public JComponent getComponent() {
        JScrollPane scrollPane = new JBScrollPane(childComponent);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        return scrollPane;
    }
}
