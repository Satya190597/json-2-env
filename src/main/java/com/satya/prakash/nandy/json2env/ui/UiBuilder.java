package com.satya.prakash.nandy.json2env.ui;


import javax.swing.*;

public class UiBuilder {
    public JComponent buildUi() {
        JComponent containerComponent = new ContainerComponent().getComponent();
        ResultPanelComponent resultPanelComponent = new ResultPanelComponent();
        JComponent resultPanel = resultPanelComponent.getComponent();
        HeaderComponent headerComponent = new HeaderComponent(containerComponent,resultPanelComponent.getjTextField(),resultPanelComponent.getjLabel());
        containerComponent.add(headerComponent.getComponent());
        containerComponent.add(resultPanel);
        ScrollPanel scrollPanel = new ScrollPanel(containerComponent);
        return scrollPanel.getComponent();
    }
}
