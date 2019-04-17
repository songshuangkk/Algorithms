package com.songshuang.algorithms.tree;

public class BST<Key extends Comparable<Key>, Value> {

  private Node root;

  private class Node {
    private Key key;

    private Value val;

    private Node left, right;

    private int N;

    public Node(Key key, Value val, int N) {
      this.key = key;
      this.val = val;
      this.N = N;
    }
  }

  public int size() {
    return size(root);
  }

  public int size(Node x) {
    if (x == null) return 0;
    else return x.N;
  }

  public Value get(Key key) {
    return get(root, key);
  }

  public Value get(Node x, Key key) {
    if (root == null) return null;

    int cmp = key.compareTo(x.key);

    if (cmp < 0) return get(x.left, key);           // 比当前节点的值要小，继续从左边遍历
    else if (cmp > 0) return get(x.right, key);     // 比当前节点的值要大，继续从右边遍历
    else return x.val;                              // 和当前节点的值相同，返回数据
  }

  public void put(Key key, Value val) {
    root = put(root, key, val);
  }

  public Node put(Node x, Key key, Value val) {
    if (x == null) return new Node(key, val, 1);

    int cmp = key.compareTo(x.key);

    if (cmp < 0) x.left = put(x.left, key, val);           // 比当前节点的值要小，继续从左边put
    else if (cmp > 0) x.right = put(x.right, key, val);     // 比当前节点的值要大，继续从右边put
    else x.val = val;                             // 和当前节点的值相同，进行一个覆盖
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public Node min() {
    return min(root);
  }

  public Node min(Node x) {
    if (x.left == null) return x;
    return min(x.left);
  }

  public Value max() {
    return max(root);
  }

  public Value max(Node x) {
    if (x.right == null) return x.val;
    return max(x.right);
  }

  public Key floor(Key key) {
    Node x = floor(root, key);
    if (x == null)return null;
    return x.key;
  }

  private Node floor(Node x, Key key) {
    if (x == null) return null;

    int cmp = key.compareTo(x.key);
    if (cmp == 0) return x;
    if (cmp < 0) return floor(x.left, key);
    Node t = floor(x.right, key);
    if (t != null) return t;
    else return x;
  }

  public void deleteMin() {
    root = deleteMin(root);
  }

  private Node deleteMin(Node x) {
    if (x.left == null) return x.right;

    x.left = deleteMin(x.left);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void deleteMax() {
    root = deleteMax(root);
  }

  private Node deleteMax(Node x) {
    if (x.right == null) return x.left;

    x.right = deleteMax(x.right);
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }

  public void delete(Key key) {
    root = delete(root, key);
  }

  private Node delete(Node x, Key key) {
    if (x == null) return null;

    int cmp = key.compareTo(x.key);
    if (cmp < 0) return delete(x.left, key);
    if (cmp > 0) return delete(x.right, key);
    else {
      // 找到要删除的节点
      if (x.right == null) return x.left;
      if (x.left == null) return x.right;

      // 找到最小的节点，并赋值给
      Node t = x;
      x = min(t);

      // 被删除节点的左子树继续链接到我们的右子树的最小节点
      x.left = t.left;
      x.right = deleteMin(t.right);   // 返回删除最小节点的右子树
    }
    x.N = size(x.left) + size(x.right) + 1;
    return x;
  }
}
