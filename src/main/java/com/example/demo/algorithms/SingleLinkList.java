package com.example.demo.algorithms;

/**
 * 单链表
 * 主要算法：
 * 判断是否是回文
 * 单链表反转
 * 判断是否成环
 * 两个有序链表合并
 * 删除链表第n 个节点
 * 寻找链表中间节点
 *
 * @author chen qi
 */
public class SingleLinkList {

    private Node head = null;
    private int n = 0;
    private static final int CACHE_CAPACITY = 20;

    private Node findByValue(int value){
        Node p = head;
        while(p != null && p.data != value){
            p = p.next;
        }
        return p;
    }

    private Node findByIndex(int index){
        Node p  = head;
        int n = 0;
        while(p != null && n < index){
            p = p.next;
            ++n;
        }
        return p;
    }

    private Node findTail(){
        if(head ==  null){
            return null;
        }
        Node p = head;
        while (p.next != null){
            p = p.next;
        }
        return p;
    }

    private void insertToHead(Node newNode){
        if(head == null){
            head = newNode;
        }else{
            newNode.next = head;
            head = newNode;
        }
    }

    private void insertTailAll(int[] values){
        for(int value : values){
            insertTail(value);
        }
    }

    private void insertTail(int value){
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
     * @param p 结束节点
     */
    private Node inverseLinkList(Node p){
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
     */
    private boolean TFResult(Node left, Node right){
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
     * 寻找链表中间节点
     * @return node 链表节点
     */
    private Node findCentreNode(){
        Node p = head;
        Node pre = head;
        if(p == null){
            return null;
        }
        while(pre.next != null && pre.next.next != null){
            p = p.next;
            pre = pre.next.next;
        }
        return p;
    }

    /**
     * 环状检测
     */
    private boolean checkCircle(){
        if(head == null){
            return false;
        }

        Node p = head;
        Node q = head;

        do {
            p = p.next;
            q = q.next.next;
            if(p == q){
                return true;
            }
        }while (q.next != null && q.next.next != null);

        return false;
    }

    private boolean checkCircle1(){
        if(head == null){
            return false;
        }

        Node p = head;

        while(p != null){
            p = p.next;
            if(p == head){
                return true;
            }
        }

        return false;
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
        Node q;
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
        link.insertTailAll(data);
        System.out.println("打印原始:");
        link.printAll();
        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
        //判断结束

        //寻找中间节点
        SingleLinkList link1 = new SingleLinkList();
        int[] data1 = {1,2,3,2,1};
        link1.insertTailAll(data1);
        System.out.println("寻找中间节点开始，原始数据为：");
        link1.printAll();
        Node centre = link1.findCentreNode();
        if(centre == null){
            System.out.println("链表为空无中间节点");
        }else{
            System.out.println("中间节点：" + centre.getData());
        }
        //end

        //单链表反转
        SingleLinkList link2 = new SingleLinkList();
        int[] data2 = {1,2,3,4,5};
        link2.insertTailAll(data2);
        System.out.println("单链表反转,原始数据：");
        link2.printAll();
        Node tail = link2.findTail();
        Node r = link2.inverseLinkList(tail);
        System.out.println("反转后结果：");
        while(r != null){
            System.out.println(r.getData());
            r = r.next;
        }
        //end

        //判断是否成环
        System.out.println("检测单链表是否是环");
        SingleLinkList link3 = new SingleLinkList();
        link3.head = createNode(1);
        Node p1 = createNode(2);
        Node p2 = createNode(3);
        link3.head.next = p1;
        p1.next = p2;
        System.out.println("检测结果：" + link3.checkCircle());
        System.out.println("检测结果：" + link3.checkCircle1());
        //end

        //两个有序链表合并


        //end

        //删除链表第n 个节点

        //end
    }

}
