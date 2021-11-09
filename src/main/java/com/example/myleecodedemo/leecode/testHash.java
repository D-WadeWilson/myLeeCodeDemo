package com.example.myleecodedemo.leecode;

import java.util.HashMap;

public class testHash {

    public static void main(String[] args) {

    }
    public static Node copyListWithRand1(Node head){
        HashMap<Node,Node> map = new HashMap<Node,Node>();
        Node cur = head;
        while(cur!=null){    //key存node老节点，value存老节点对应克隆出来的新节点1'。按此规律将老节点依次遍历。
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        while(cur!=null){
        //cur 老节点  ; map.get(cur) 新节点
            map.get(cur).next =  map.get(cur.next);
            map.get(cur).rand =  map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }
    public static Node copyListWithRand2(Node head){
        if(head==null){
            return null;
        }
        Node cur = head;
        Node next = null;
        while (cur!=null){  //克隆节点，然后把克隆的节点就直接放在老链表的下一个。
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        cur = head;
        Node curCopy = null;
        while (cur!=null){  //1的rand指针3,3再取next指针3'，即为1'的rand指针指向。
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand!=null?cur.rand.next:null;
            cur = next;
        }
        Node res = head.next;
        cur = head;
        while (cur!=null){  //最后，在next方向上，将新老链表分离出来，即完成了新链表的创建。
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next;
            curCopy.next = next!=null?next.next:null;
            cur = next;
        }
        return res;
    }


    //如果两个链表都无环，返回第一个
    public static Node noLoop(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next!=null){
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next!=null){
            n--;
            cur2 = cur2.next;
        }
        if(cur1!=cur2){   //两个链表不收于同一点，即代表没有交点
            return null;
        }
        cur1 = n > 0 ? head1 : head2;  //谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; //谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n!=0){
            n--;
            cur1 = cur1.next;
        }
        while (cur1!=cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return  cur1;
    }
    //两个有环链表，返回第一个相交节点。不相交返回null      loop:入环的第一个节点
    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        Node cur1 = null;
        Node cur2 = null;
        if(loop1 == loop2){   //两条链表入同一个环,则和前一个noloop方法类似
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1){
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop1){
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;  //谁长，谁的头变成cur1
            cur2 = cur1 == head1 ? head2 : head1; //谁短，谁的头变成cur2
            n = Math.abs(n);
            while (n!=0){
                n--;
                cur1 = cur1.next;
            }
            while (cur1!=cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return  cur1;
        }else {  //两条链表入两个环
            cur1 = loop1.next;
            while (cur1!=loop1){
                if (cur1==loop2){  //如果在环上相遇第二个入环节点。说明同环
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;        //绕完一圈没有遇到第二个入环节点。说明不相交，返回null
        }
    }

}
