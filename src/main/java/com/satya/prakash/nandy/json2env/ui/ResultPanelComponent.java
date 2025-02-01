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
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ResultPanelComponent implements JsonToEnvComponent {
    private final JTextField jTextField;
    private final JLabel jLabel;
    private final String HTML_SUB_HEADING = "<html><b>Get Started</b>\n" +
            "                                        <ul>\n" +
            "                                        <li>Click <b>Add</b> to begin.</li>\n" +
            "                                          <li>Enter your JSON text into the designated field.</li>\n" +
            "                                          <li>(Optional) Provide a prefix to customize the output keys.</li>\n" +
            "                                          <li>Click <b>Convert</b> to process the JSON data.</li>\n" +
            "                                          <li>The generated output will be formatted as environment variables, ready for use in IntelliJ.</li>\n" +
            "                                        </ul><b>Result : </b></html>";

    ResultPanelComponent() {
        this.jTextField = new JTextField();
        this.jLabel = new JLabel();
    }

    @Override
    public JComponent getComponent() {
        JPanel jPanel = new JPanel(new VerticalFlowLayout());
        jPanel.add(new JSeparator());
        JLabel label = new JLabel(HTML_SUB_HEADING);
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

    public JComponent getRepoAndDocLinkButton() {
        JButton jButton = new JButton("Repository And Documentation.");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Satya190597/json-2-env"));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (URISyntaxException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        return jButton;
    }

    private ActionListener getCopyToClipboardActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = jTextField.getText();
                if (Strings.isBlank(text)) return;
                StringSelection stringSelection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                JFrame frame = new JFrame("Popup Alert Example");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 150);
                JOptionPane.showMessageDialog(frame, "Copied to Clipboard.", "Copied", JOptionPane.INFORMATION_MESSAGE);
            }
        };
    }
}
