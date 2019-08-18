package com.yyh.util;

import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FindNodeByFilter {

    public static String FILTER_TEXT = "";
    public static Set<String> filterPassNode;

    public static void find(AbstractTreeNode root) {

        filterPassNode = new HashSet<>();
        recursiveFind(root, filterPassNode);

    }

    private static void recursiveFind(AbstractTreeNode parentNode, Set<String> filterPassNode) {

        Collection<AbstractTreeNode> childNodes = parentNode.getChildren();
        for (AbstractTreeNode childNode : childNodes) {
            if (childNode instanceof PsiFileNode) {
                VirtualFile vf  = ((PsiFileNode) childNode).getVirtualFile();
                if (vf != null && !vf.isDirectory() && !(vf.getFileType() instanceof PlainTextFileType)) {
                    continue;
                }

                final String fileName = vf.getName();
                if (fileName.contains(FILTER_TEXT)) {
                    findUp(vf, filterPassNode);

                }
            }

            if (!childNode.getChildren().isEmpty()) {
                recursiveFind(childNode, filterPassNode);

            }

        }

    }

    private static void findUp(VirtualFile children, Set<String> filterPassNode) {

        filterPassNode.add(children.getPath());

        if (children.getParent() != null) {
            findUp(children.getParent(), filterPassNode);

        }

    }

}
