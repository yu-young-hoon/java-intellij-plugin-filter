package com.yyh.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.yyh.util.FindNodeByFilter;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;

public class FilterPopupDialog extends DialogWrapper {

    private JTextArea filterArea;

    public FilterPopupDialog() {

        super(true); // use current window as parent
        init();

    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {

        JPanel dialogPanel = new JPanel(new BorderLayout());

        this.filterArea = new JTextArea();
        filterArea.setText(FindNodeByFilter.FILTER_TEXT);
        dialogPanel.add(filterArea);

        return dialogPanel;

    }

    public String getText() {
        return this.filterArea.getText();

    }

}
