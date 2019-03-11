package com.itliusir.test.linked;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 链表
 *
 * @author liugang
 * @since 2019/3/6
 */

@Slf4j
public class LinkedNode {

    /**
     * node1: 3 5 6     1 4 7 2
     * node2: 9 6 3 6 5 1 4 7 2
     *
     * result : 1
     *
     * @author liugang 2019/3/6 11:07
     */
    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node node12 = new Node(5);
        Node node13 = new Node(6);

        Node node2 = new Node(9);
        Node node21 = new Node(6);
        Node node22 = new Node(3);
        Node node23 = new Node(6);
        Node node24 = new Node(5);

        Node node34 = new Node(1);
        Node node35 = new Node(4);
        Node node36 = new Node(7);
        Node node37 = new Node(2);

        node1.next = node12;
        node12.next = node13;
        node13.next = node34;

        node2.next = node21;
        node21.next = node22;
        node22.next = node23;
        node23.next = node24;
        node24.next = node34;

        node34.next = node35;
        node35.next = node36;
        node36.next = node37;

        Node result = node(node1, node2);
        log.info("result:{}", result);

    }

    private static Node node(Node node1, Node node2) {
        int length1 = 0;
        int length2 = 0;
        Node nodeA = node1;
        Node nodeB = node2;
        while (null != node1.next) {
            length1++;
            node1 = node1.next;
        }

        while (null != node2.next) {
            length2++;
            node2 = node2.next;
        }
        if (length1 > length2) {
            for (int i = 0; i < length1 - length2; i++) {
                nodeA = nodeA.next;
            }
        } else {
            for (int i = 0; i < length2 - length1; i++) {
                nodeB = nodeB.next;

            }
        }

        while (null != nodeA && null != nodeB) {
            if (nodeA == nodeB) {
                return nodeA;
            } else {
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }
        return null;
    }

    @Data
    static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
