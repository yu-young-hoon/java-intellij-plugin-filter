package com.yyh.action;

import com.intellij.ide.projectView.ProjectView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ReleaseFilterMenuAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {

        FindNodeByFilter.FILTER_TEXT = "";
        ProjectView.getInstance(Objects.requireNonNull(event.getProject())).refresh();

    }
}
