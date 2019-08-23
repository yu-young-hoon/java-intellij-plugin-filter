package com.yyh.action;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.yyh.dialog.FilterPopupDialog;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FilterPopupMenuAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        final FilterPopupDialog filterPopupDialog = new FilterPopupDialog();
        if (filterPopupDialog.showAndGet()) {
            FindNodeByFilter.FILTER_TEXT = filterPopupDialog.getText();
            FindNodeByFilter.FILTER_PASS_NODE = null;

            ProjectView.getInstance(Objects.requireNonNull(event.getProject())).refresh();

        }
    }
}
