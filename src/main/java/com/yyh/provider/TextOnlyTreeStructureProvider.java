package com.yyh.provider;
import com.intellij.ide.projectView.*;
import com.intellij.ide.projectView.impl.nodes.ExternalLibrariesNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.yyh.util.FindNodeByFilter.FILTER_PASS_NODE;
import static com.yyh.util.FindNodeByFilter.FILTER_TEXT;

public class TextOnlyTreeStructureProvider implements TreeStructureProvider {

    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parentNode,
                                               @NotNull Collection<AbstractTreeNode> children,
                                               ViewSettings settings) {

        if (!"".equals(FILTER_TEXT) &&
                !(parentNode instanceof ExternalLibrariesNode)) {
            FindNodeByFilter.find(parentNode);

        }

        ArrayList<AbstractTreeNode> nodes = new ArrayList<>();
        for (AbstractTreeNode childNode : children) {
            childNode.setParent(parentNode);

            if ("".equals(FILTER_TEXT) ||
                    childNode instanceof ExternalLibrariesNode ||
                    FILTER_PASS_NODE.contains(childNode.getValue())) {
                nodes.add(childNode);

            }

        }

        return nodes;

    }

    @Nullable
    @Override
    public Object getData(Collection<AbstractTreeNode> collection, String s) {

        return null;

    }

}