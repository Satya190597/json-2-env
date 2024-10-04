package com.satya.prakash.nandy.json2env.ui;

import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.JBColor;
import com.jgoodies.common.base.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultPanelComponent implements JsonToEnvComponent {
    private final JTextField jTextField;
    private final JLabel jLabel;
    ResultPanelComponent() {
        this.jTextField = new JTextField();
        this.jLabel = new JLabel();
    }
    @Override
    public JComponent getComponent() {
        JPanel jPanel = new JPanel(new VerticalFlowLayout());
        jPanel.add(new JSeparator());
        JLabel label = new JLabel("<html>" +
                "<ul>" +
                "<li><b>Json 2 Env</b> is a utility plugin to convert JSON text to environment variables.</li>" +
                "<li>You can convert up to <b>12 JSON texts</b> at once.</li>" +
                "<li>An optional <b>prefix</b> can be added for each JSON text.</li>" +
                "<li>To get started, click on <b>Add</b>.</li>" +
                "<li>Once you enter your JSON text and prefix (if required), click on <b>Convert</b>.</li>" +
                "<li>The output will provide you with environment text that can be directly used in IntelliJ.</li>" +
                "<li><b>Nested JSON structures are not supported.</b></li>" +
                "</ul>" +
                "<span><b>Result</b></span>"+
                "</html>");
        jPanel.add(label);
        jPanel.add(jTextField);
        jPanel.add(jLabel);
        jLabel.setVisible(false);
        jLabel.setForeground(JBColor.RED);
        jPanel.add(getCopyButton());
        jPanel.add(new JSeparator());
        return jPanel;
    }

    public JLabel getjLabel() {
        return jLabel;
    }

    public JTextField getjTextField() {
        return jTextField;
    }

    public JComponent getCopyButton() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton jButton = new JButton("Copy To Clipboard");
        jButton.addActionListener(getCopyToClipboardActionListener());
        jPanel.add(jButton);
        return jPanel;
    }

    private ActionListener getCopyToClipboardActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextField.getText();
                if(Strings.isBlank(text))
                    return;
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection,null);
                JFrame frame = new JFrame("Popup Alert Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150);
                JOptionPane.showMessageDialog(frame,"Copied to Clipboard.", "Copied", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
}
