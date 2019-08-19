package com.yyh.util;

import com.intellij.ide.util.treeView.AbstractTreeNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FindNodeByFilter {

    public static String FILTER_TEXT = "";
    public static Set<Object> FILTER_PASS_NODE;

    public static void find(AbstractTreeNode root) {

        if (FILTER_PASS_NODE == null) {
            FILTER_PASS_NODE = new HashSet<>();

        }

        recursiveFind(root, FILTER_PASS_NODE);

    }

    private static void recursiveFind(AbstractTreeNode parentNode, Set<Object> filterPassNode) {

        Collection<AbstractTreeNode> childNodes = parentNode.getChildren();
        for (AbstractTreeNode childNode : childNodes) {
            childNode.setParent(parentNode);

            if (!filterPassNode.contains(childNode.getValue())) {
                final String fileName = childNode.getValue().toString();
                if (fileName.contains(FILTER_TEXT)) {
                    findUp(childNode, filterPassNode);

                }
            }

            if (!childNode.getChildren().isEmpty()) {
                recursiveFind(childNode, filterPassNode);

            }

        }

    }

    private static void findUp(AbstractTreeNode children, Set<Object> filterPassNode) {

        filterPassNode.add(children.getValue());
        if (children.getParent() != null) {
            findUp(children.getParent(), filterPassNode);

        }

    }

}
