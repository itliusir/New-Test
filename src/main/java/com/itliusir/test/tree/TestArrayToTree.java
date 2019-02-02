package com.itliusir.test.tree;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *
 * 6
 * /   \
 * 3     5
 * \    /
 * 2  0
 * \
 * 1
 *
 * @author liugang
 * @since 2019/1/14
 */
@Slf4j
public class TestArrayToTree {

    public static void main(String[] args) {
        int[] arr = {3, 2, 1, 0,0, 6, 5};
        Node node = new TestArrayToTree().getRootNodeValue(arr);
        int rootNodeValue = node.value;
        log.info("rootNodeValue:{}", rootNodeValue);
    }

    public Node getRootNodeValue(int[] arr) {
        int maxValue = 0;
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
                maxIndex = i;
            }
        }
        Node node = new Node(maxValue);
        int[] leftArr = new int[maxIndex];
        int[] rightArr = new int[arr.length - maxIndex - 1];
        System.arraycopy(arr, 0, leftArr, 0, maxIndex);
        System.arraycopy(arr, maxIndex + 1, rightArr, 0, arr.length - maxIndex - 1);
        node.left = leftArr.length == 0 ? null : getRootNodeValue(leftArr);
        node.right = rightArr.length == 0 ? null : getRootNodeValue(rightArr);
        return node;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
