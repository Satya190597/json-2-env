package com.satya.prakash.nandy.json2env.ui;

import com.satya.prakash.nandy.json2env.processor.JsonToEnvProcessor;
import com.satya.prakash.nandy.json2env.repo.JsonToEnvRepo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HeaderComponent implements JsonToEnvComponent {
    private final JComponent parentComponent;
    private final JsonToEnvRepo jsonToEnvRepo;
    private final JsonToEnvProcessor jsonToEnvProcessor;
    private final JTextField resultTextField;
    private final JLabel errorLabel;

    HeaderComponent(JComponent parentComponent,JTextField resultTextField,JLabel errorLabel) {
        this.parentComponent = parentComponent;
        this.jsonToEnvRepo = JsonToEnvRepo.getRepo();
        this.jsonToEnvProcessor = new JsonToEnvProcessor();
        this.resultTextField = resultTextField;
        this.errorLabel = errorLabel;
    }

    @Override
    public JComponent getComponent() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.add(createButton("Add",getAddButtonActionListener()));
        panel.add(createButton("Convert", getCovertButtonActionListener()));
        panel.add(createButton("Clear All",getClearAllButtonActionListener()));
        return panel;
    }

    private JButton createButton(String text, ActionListener actionListener) {
        JButton addButton = new JButton(text);
        addButton.addActionListener(actionListener);
        return addButton;
    }

    private ActionListener getAddButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jsonToEnvRepo.isLimitExceed())
                    return;
                JsonInputComponent jsonInputComponent = new JsonInputComponent(parentComponent);
                jsonToEnvRepo.addComponent(jsonInputComponent);
                parentComponent.add(jsonInputComponent.getComponent());
                parentComponent.revalidate();
                parentComponent.repaint();
            }
        };
    }

    private ActionListener getCovertButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clearErrorLabel();
                    String text = jsonToEnvProcessor.generateEnvText();
                    showResult(text);
                }
                catch (Exception exception) {
                    showErrorLabel(exception.getMessage());
                }
            }
        };
    }

    private ActionListener getClearAllButtonActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearErrorLabel();
                List<JsonInputComponent> jsonInputComponentList = jsonToEnvRepo.getAllComponent();
                for (JsonInputComponent jsonInputComponent : jsonInputComponentList) {
                    parentComponent.remove(jsonInputComponent.getJsonInputComponent());
                    jsonToEnvRepo.removeComponent(jsonInputComponent);

                }
                clearResult();
            }
        };
    }

    private void clearErrorLabel() {
        errorLabel.setText("");
        errorLabel.setVisible(false);
    }
    private void showErrorLabel(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
    }
    private void clearResult() {
        resultTextField.setText("");
        parentComponent.revalidate();
        parentComponent.repaint();
    }
    private void showResult(String text) {
        resultTextField.setText(text);
        parentComponent.revalidate();
        parentComponent.repaint();
    }
}
