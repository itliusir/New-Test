package com.itliusir.test.tree;

import com.itliusir.test.tree.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * List -> Tree
 *
 * @author liugang
 * @since 2018/10/31
 */

@Slf4j
public class TestListToTree {
    private static List<TreeNode> list = new ArrayList<>();

    static {
        TreeNode treeNode1 = new TreeNode("1", "-1", "一级菜单1", 1, null);
        TreeNode treeNode2 = new TreeNode("2", "-1", "一级菜单2", 2, null);
        TreeNode treeNode3 = new TreeNode("3", "-1", "一级菜单3", 3, null);
        TreeNode treeNode11 = new TreeNode("1-1", "1", "二级菜单1-1", 1, null);
        TreeNode treeNode111 = new TreeNode("1-1-1", "1-1", "三级菜单1-1-1", 1, null);
        TreeNode treeNode22 = new TreeNode("2-1", "2", "二级菜单2-1", 1, null);
        list.add(treeNode1);
        list.add(treeNode2);
        list.add(treeNode3);
        list.add(treeNode11);
        list.add(treeNode111);
        list.add(treeNode22);
    }


    public static void main(String[] args) {
        // List -> Tree
        TestListToTree tltt = new TestListToTree();
        log.info("start time:{}",System.currentTimeMillis());
        List<TreeNode> treeNodes = tltt.buildTree();
        log.info("end time:{}",System.currentTimeMillis());
    }

    private List<TreeNode> buildTree() {
        List<TreeNode> pTreeNodes = this.listByPId(list, "-1");
        return listToTree(list, pTreeNodes);
    }

    private List<TreeNode> listToTree(List<TreeNode> list, List<TreeNode> pList) {
        for (TreeNode treeNode : pList) {
            List<TreeNode> reVos = this.listByPId(list, treeNode.getId());
            if (!reVos.isEmpty()) {
                treeNode.setChildren(reVos);
                this.listToTree(list, reVos);
            }
        }
        return pList;
    }

    /**
     * 根据ParentId查询父节点列表
     *
     * @author liugang 2018/10/31 15:42
     */
    private List<TreeNode> listByPId(List<TreeNode> tns, String parentId) {
        List<TreeNode> reVos = new ArrayList<>();
        for (TreeNode tn : tns) {
            if (Objects.equals(tn.getParentId(), parentId)) {
                reVos.add(tn);
            }
        }
        return reVos;
    }
}
