package com.example.demo.algorithms;

/**
 * 单链表
 * 主要算法：判断是否是回文
 * @author chen qi
 */
public class SingleLinkList {

    public Node head = null;
    public int n = 0;
    public static final int CACHE_CAPACITY = 20;

    public Node findByValue(int value){
        Node p = head;
        while(p != null && p.data != value){
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index){
        Node p  = head;
        int n = 0;
        while(p != null && n < index){
            p = p.next;
            ++n;
        }
        return p;
    }

    public void insertToHead(Node newNode){
        if(head == null){
            head = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertTail(int value){
        Node tailNode = new Node(value, null);

        if(head == null){
            head = tailNode;
        }else{
            Node q = head;
            while(q.next != null){
                q = q.next;
            }
            q.next = tailNode;
        }
    }

    public void insertAfter(Node p,int value){
        Node newNode = new Node(value,null);
        insertAfter(p,newNode);
    }

    public void insertAfter(Node p,Node newNode){
        if(p == null){
            return;
        }
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value){
        Node newNode = new Node(value,null);
        insertBefore(p,newNode);
    }

    public void insertBefore(Node p, Node newNode){
        if(p == null){
            return;
        }
        if(head == p){
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while(q.next != p){
            q = q.next;
        }
        if(q == null){
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    public void deleteByNode(Node p){
        if(p == null || head == null){
            return;
        }
        if(head == p){
            head  = head.next;
            return;
        }
        Node q = head;
        while(q.next != p){
            q = q.next;
        }
        if(q == null){
            return;
        }
        q.next = p.next;
    }

    public void deleteByValue(int value){
        if(head == null){
            return;
        }
        Node p = head;
        while(p.next != null){
            if(p.next.data == value){
                p.next = p.next.next;
            }else {
                p = p.next;
            }
        }
    }

    public void printAll(){
        Node p = head;
        while(p != null){
            System.out.println(p.data+ "");
            p = p.next;
        }
        System.out.println();
    }

    /**
     * 判断是否是回文字符
     * @return
     */
    public boolean palindrome(){
        if(head == null){
            return false;
        }else{
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            while(q.next != null && q.next.next != null){
                p = p.next;
                q = q.next.next;
            }
            System.out.println("中间节点"+p.data);
            System.out.println("开始执行奇数节点的回文判断");
            Node leftLink;
            Node rightLink;
            if(q.next == null){
                //奇数
                 rightLink = p.next;
                 leftLink = inverseLinkList(p).next;
            }else{
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            boolean result =  TFResult(rightLink,leftLink);
            return result;
        }
    }

    /**
     * 翻转链表(从头到p的位置)
     * @param p
     * @return
     */
    public Node inverseLinkList(Node p){
        Node pre = null;
        Node r = head;
        Node next;

        while (r != p){
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }

        r.next = pre;
        return r;
    }

    /**
     * 判断两个链表中的值是否一致
     * @param left
     * @param right
     * @return
     */
    public boolean TFResult(Node left, Node right){
        Node l = left;
        Node r = right;
        System.out.println("left:" + l.data);
        System.out.println("right:" + r.data);
        while (l != null && r != null){
            if(l.data != r.data){
                return false;
            }
            l = l.next;
            r = r.next;
        }
        if(l == null && r == null){
            return true;
        }else {
            return false;
        }
    }

    /**
     * LRU算法
     */
    public void addCache(int value){
        if(head == null){
            head = new Node(value,null);
            ++n;
            return;
        }
        if(head.data == value){
            Node p = new Node(value,null);
            p.next = head;
            head = p;
            ++n;
            if(n > CACHE_CAPACITY){
                //删除最后一个;
            }
            return;
        }
        Node p = head;
        Node q = null;
        while (p.next != null){
            if(p.next.data == value){
                q = p.next;
                p.next = p.next.next;
                q.next = head;
                head = q;
                return;
            }
            p = p.next;
        }
        //判断n大小,如果小于内存直接添加在头部，如果大于先删除末尾再添加
    }

    public static class Node{
        private int data;
        private Node next;

        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        public int getData(){
            return data;
        }
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    public static void main(String[] args) {
        //判断是否是回文
        SingleLinkList link = new SingleLinkList();
        int[] data = {1,2,5,2,1};
        for(int i =0; i < data.length; i++){
            link.insertTail(data[i]);
        }
        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
        //判断结束

    }

}
