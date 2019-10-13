/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.github.dreamroute.mybatis.fly;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BinTree {

    private TreeNode root = null;

    // 构造方法
    public BinTree() {
        this.root = new TreeNode(1, "A");
    }

    /**
     * 创建二叉树
     * <pre>
     *           A
     *     B          C
     *  D     E            F
     * </pre>
     */
    public void create(TreeNode root) {
        TreeNode b = new TreeNode(2, "B");
        TreeNode c = new TreeNode(2, "C");
        TreeNode d = new TreeNode(3, "D");
        TreeNode e = new TreeNode(3, "E");
        TreeNode f = new TreeNode(3, "F");
        root.left = b;
        root.right = c;
        b.left = d;
        b.right = e;
        c.right = f;
    }
    
    // 是否为空树
    public boolean isEmpty() {
        return root == null;
    }
    
    // 树的高度
    public int height() {
        return this.height(root);
    }
    
    public int height(TreeNode subNode) {
        if (subNode == null)
            return 0;
        else {
            int lh = this.height(subNode.left);
            int rh = this.height(subNode.right);
            return lh < rh ? rh + 1 : lh + 1;
        }
    }
    
    // 整棵树的节点个数
    public int size() {
        return size(root);
    }
    
    public int size(TreeNode subNode) {
        if (root == null)
            return 0;
        else
            return 1 + size(subNode.left) + size(subNode.right);
    }
    
    // 返回双亲节点
    public TreeNode parent(TreeNode node) {
        return (root == null || node == root) ? null : this.parent(root, node); 
    }
    
    public TreeNode parent(TreeNode subNode, TreeNode node) {
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        private int key = 0;
        private String data;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int key, String data) {
            this.key = key;
            this.data = data;
        }
    }

}
