package com.satya.prakash.nandy.json2env.ui;

import com.intellij.openapi.ui.VerticalFlowLayout;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JsonInputComponent implements JsonToEnvComponent {
    private JTextField prefixTextField;
    private JTextField jsonTextField;
    private final JComponent parentComponent;
    private JComponent jsonInputComponent;
    private final JsonToEnvRepo jsonToEnvRepo;
    JsonInputComponent(JComponent parentComponent) {
        this.parentComponent = parentComponent;
        jsonToEnvRepo = JsonToEnvRepo.getRepo();
    }
    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel(new VerticalFlowLayout());
        panel.add(getLabel("Prefix (Optional)"));
        this.prefixTextField = getTextField();
        panel.add(this.prefixTextField);
        panel.add(getLabel("Enter Your Json"));
        this.jsonTextField = getTextField();
        panel.add(this.jsonTextField);
        panel.add(getDeleteButton());
        this.jsonInputComponent = panel;
        return this.jsonInputComponent;
    }
    public JTextField getJsonTextField() {
        return jsonTextField;
    }
    public JTextField getPrefixTextField() {
        return prefixTextField;
    }
    public JComponent getJsonInputComponent() {
        return jsonInputComponent;
    }
    private JComponent getDeleteButton() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(deleteButtonActionListener());
        jPanel.add(deleteButton);
        return jPanel;
    }
    private JLabel getLabel(String text) {
        return new JLabel(text);
    }
    private JTextField getTextField() {
        return new JTextField();
    }
    private ActionListener deleteButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonToEnvRepo.removeComponent(JsonInputComponent.this);
                parentComponent.remove(jsonInputComponent);
                parentComponent.revalidate();
                parentComponent.repaint();;
            }
        };
    }
}
