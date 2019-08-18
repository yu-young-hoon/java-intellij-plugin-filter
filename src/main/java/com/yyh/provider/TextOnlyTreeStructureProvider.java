package com.yyh.provider;
import com.intellij.ide.projectView.*;
import com.intellij.ide.projectView.impl.nodes.PsiDirectoryNode;
import com.intellij.ide.projectView.impl.nodes.PsiFileNode;
import com.intellij.ide.util.treeView.AbstractTreeNode;
import com.intellij.openapi.fileTypes.PlainTextFileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.yyh.util.FindNodeByFilter;
import org.jetbrains.annotations.*;

import java.util.*;

import static com.yyh.util.FindNodeByFilter.FILTER_TEXT;

public class TextOnlyTreeStructureProvider implements TreeStructureProvider {

    private static boolean expand = false;

    @NotNull
    @Override
    public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent,
                                               @NotNull Collection<AbstractTreeNode> children,
                                               ViewSettings settings) {

        if (FindNodeByFilter.filterPassNode == null) {
            FindNodeByFilter.find(findRoot(parent));
        }

        ArrayList<AbstractTreeNode> nodes = new ArrayList<>();
        for (AbstractTreeNode child : children) {
            if (child instanceof PsiFileNode) {
                VirtualFile file  = ((PsiFileNode) child).getVirtualFile();
                if (file != null && !file.isDirectory() &&
                        !(file.getFileType() instanceof PlainTextFileType)) {

                    continue;
                }

                if ("".equals(FILTER_TEXT) ||
                        FindNodeByFilter.filterPassNode.contains(file.getPath())) {

                    nodes.add(child);

                }
            } else if(child instanceof PsiDirectoryNode) {

                if (!child.getChildren().isEmpty()) {
                    expand = true;

                }

                if ("".equals(FILTER_TEXT) ||
                        FindNodeByFilter.filterPassNode.contains(((PsiDirectoryNode) child).getVirtualFile().getPath())) {

                    nodes.add(child);

                }
            } else {

                nodes.add(child);

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

        FindNodeByFilter.filterPassNode = null;
        if (true == expand) {
            expand = false;
            return true;

        }

        return expand;

    }

    private AbstractTreeNode findRoot(AbstractTreeNode node) {

        if (node.getParent() == null) {
            return node;
        }

        return findRoot(node.getParent());

    }

}