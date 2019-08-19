package com.yyh.provider;
import com.intellij.ide.projectView.*;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.yyh.util.FindNodeByFilter.FILTER_TEXT;

public class TextOnlyTreeStructureProvider implements TreeStructureProvider {

    private static boolean expand = false;

    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parentNode,
                                               @NotNull Collection<AbstractTreeNode> children,
                                               ViewSettings settings) {
        if (!"".equals(FILTER_TEXT)){
            FindNodeByFilter.find(parentNode);
        }

        ArrayList<AbstractTreeNode> nodes = new ArrayList<>();
        for (AbstractTreeNode childNode : children) {
            childNode.setParent(parentNode);

            if ("".equals(FILTER_TEXT) ||
                    FindNodeByFilter.FILTER_PASS_NODE.contains(childNode.getValue())) {
                nodes.add(childNode);

            }

            if (!childNode.getChildren().isEmpty()) {
                expand = true;

            }

        }

        return nodes;

    }

    @Nullable
    @Override
    public Object getData(Collection<AbstractTreeNode> collection, String s) {
        return null;
    }

    public static boolean isExpand() {

        if (true == expand) {
            expand = false;
            return true;

        }

        return expand;

    }

}