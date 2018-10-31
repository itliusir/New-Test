package com.itliusir.test.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 节点
 *
 * @author liugang
 * @since 2018/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    private String id;

    private String parentId;

    private String title;

    private Integer order;

    private List<TreeNode> children;
}
