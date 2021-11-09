package com.example.myleecodedemo.leecode;

import jdk.internal.org.objectweb.asm.Handle;

import javax.crypto.interfaces.PBEKey;
import java.util.Stack;

public class testTree {

    public static void preOrderRecur(Node head){
        if(head==null){
            return;
        }
        System.out.print(head.value+"");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    public static void inOrderRecur(Node head){
        if(head==null){
            return;
        }
        preOrderRecur(head.left);
        System.out.print(head.value+"");
        preOrderRecur(head.right);
    }
    public static void posOrderRecur(Node head){
        if(head==null){
            return;
        }
        preOrderRecur(head.left);
        preOrderRecur(head.right);
        System.out.print(head.value+"");
    }
    //后序遍历非递归方式
    public static void posOrderRecur1(Node head){
        System.out.println("后序遍历非递归：");
        if(head==null){
            return;
        }
        Stack<Node> s1 = new Stack<Node>();
        Stack<Node> s2 = new Stack<Node>();
        s1.push(head);
        while (!s1.isEmpty()){
            head = s1.pop();
            s2.push(head);
            if(head.left!=null){
                s1.push(head.left);
            }
            if(head.right!=null){
                s1.push(head.right);
            }
        }
        while (!s2.isEmpty()){
            System.out.print(s2.pop().value+" ");

        }
    }

    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node(int data){
            this.value = data;
        }
    }
}
