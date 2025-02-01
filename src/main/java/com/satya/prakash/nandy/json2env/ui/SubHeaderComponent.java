package com.satya.prakash.nandy.json2env.ui;

import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubHeaderComponent implements JsonToEnvComponent {
    private final JsonToEnvRepo jsonToEnvRepo;
    SubHeaderComponent() {
        this.jsonToEnvRepo = JsonToEnvRepo.getRepo();
    }

    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JCheckBox dependentCheckbox = getSupportsArrayCheckbox();
        panel.add(dependentCheckbox);
        panel.add(getNestedJsonSupportCheckbox(dependentCheckbox,panel));
        return panel;
    }

    private JCheckBox getNestedJsonSupportCheckbox(JCheckBox dependentCheckbox,JPanel parentComponent) {
        JCheckBox checkBox = new JCheckBox("Analyze Nested JSON Structure.");
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonToEnvRepo.setReadNestedJson(checkBox.isSelected());
                dependentCheckbox.setVisible(checkBox.isSelected());
                parentComponent.revalidate();
                parentComponent.repaint();
                if(!checkBox.isSelected()) {
                    dependentCheckbox.setSelected(false);
                    jsonToEnvRepo.setReadArrayAsMultiValue(false);
                }
            }
        });
        return checkBox;
    }
    private JCheckBox getSupportsArrayCheckbox() {
        JCheckBox checkBox = new JCheckBox("Analyze array as multi value.");
        checkBox.setVisible(false);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonToEnvRepo.setReadArrayAsMultiValue(checkBox.isSelected());
            }
        });
        return checkBox;
    }
    private JPanel alignRight(JComponent component) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(component);
        return panel;
    }
}
