package com.songshuang.algorithms.tree.exercise;


import java.util.*;

/**
 * 将E A S Y Q U E S T I O N 作为键按顺序插入一颗初始为空的二叉树中.并画出这棵树.
 */
public class Exercise01 {

  static class Node {

    public Node() {

    }
    private String key;

    private Node left;

    private Node right;

    private int deep;

    public String getKey() {
      return key;
    }

    public void setKey(String key) {
      this.key = key;
    }

    public Node getLeft() {
      return left;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public Node getRight() {
      return right;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public int getDeep() {
      return deep;
    }

    public void setDeep(int deep) {
      this.deep = deep;
    }
  }

  private static Node createNode() {
    return new Node();
  }

  private static Node put(Node x, String key, int deep) {
    deep = deep + 1;
    if (x == null) {
      x = new Node();
      x.setKey(key);
      x.setDeep(deep);
      return x;
    }
    if (x.getKey() == null) {
      x.setDeep(deep);
      x.setKey(key);
      return x;
    }

    int cmp = key.compareTo(x.getKey());
    if (cmp < 0) {
      x.setLeft(put(x.getLeft(), key, deep));
    } else if (cmp > 0) {
      x.setRight(put(x.getRight(), key, deep));
    } else {
      x.setKey(key);
    }

    return x;
  }

  private static void print(Node root) {
    Map<Integer, List<String>> map = new HashMap<>();
    traversal(root, map, false, 1);
    List<Integer> keys = new ArrayList<>(map.keySet());
    Collections.sort(keys);
    keys.forEach(item -> {
      map.get(item).forEach(key -> {
        System.out.printf(" %s", key);
      });
      System.out.printf("\n");
    });
  }

  private static int traversal(Node x, Map<Integer, List<String>> map, boolean isRight, int offset) {

    map.computeIfAbsent(x.getDeep(), k -> new ArrayList<>());

    if (x.getLeft() != null) {
      offset = traversal(x.getLeft(), map, false, 1) + 1;
    }

    StringBuilder stringBuilder = new StringBuilder();
    if (x.getRight() != null) {
      offset = traversal(x.getRight(), map, true, offset-1);
    }

    for (int i=0; i<offset; i++) {
      stringBuilder.append(" ");
    }
    if (isRight) {
      stringBuilder.append(" ");
    }
    stringBuilder.append(x.getKey());
    map.get(x.getDeep()).add(stringBuilder.toString());

    return offset;
  }


  public static void main(String[] args) {
    List<String> keys = new ArrayList<>();
    keys.add("E");
    keys.add("A");
    keys.add("S");
    keys.add("Y");
    keys.add("Q");
    keys.add("U");
    keys.add("E");
    keys.add("C");
    keys.add("T");
    keys.add("I");
    keys.add("O");
    keys.add("N");

    Node root = createNode();

    keys.forEach(item -> {
      put(root, item, 0);
    });

    print(root);

  }
}
