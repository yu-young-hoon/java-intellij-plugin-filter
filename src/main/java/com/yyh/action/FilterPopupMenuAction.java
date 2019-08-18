package com.yyh.action;

import com.intellij.ide.actions.tree.FullyExpandTreeNodeAction;
import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.yyh.dialog.FilterPopupDialog;
import com.yyh.provider.TextOnlyTreeStructureProvider;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.NotNull;

public class FilterPopupMenuAction extends AnAction {


    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        final FilterPopupDialog filterPopupDialog = new FilterPopupDialog();
        if (filterPopupDialog.showAndGet()) {
            FindNodeByFilter.FILTER_TEXT = filterPopupDialog.getText();

            ProjectView.getInstance(event.getProject()).refresh();

            while (TextOnlyTreeStructureProvider.isExpand()) {
                new FullyExpandTreeNodeAction().actionPerformed(event);

            }

            FindNodeByFilter.filterPassNode = null;

        }
    }
}
