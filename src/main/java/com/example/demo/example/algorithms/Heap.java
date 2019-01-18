package com.example.demo.example.algorithms;

/**
 * @author chenqi
 */
public class Heap {
    private int[] data ;
    private int length;
    /**堆可以存储的最大个数*/
    private int n;

    public Heap(int length) {
        this.data = new int[length + 1];
        this.length = 0;
        this.n = length;
    }

    public boolean put(int value){
        //堆已满
        if(length >= n){
            return false;
        }

        ++length;
        //堆为空
        if(length == 0){
            data[1] = value;
        }

        data[length] = value;
        circleSwap(length);

        return true;
    }

    public boolean removeHead(){
        if(length == 0){
            return false;
        }

        data[1] = data[length];
        data[length] = 0;
        --length;
        if(length == 1){
            return true;
        }

        headify(1);
        return true;
    }

    private void headify(int p){
        int q;
        int left;
        int right;
        while(length >= p << 1){
            left = p << 1;
            right = (p << 1) +1;
            q = left;
            if(n >= right){
                if(data[left] < data[right]){
                    q = right;
                }
            }

            if(data[p] < data[q]){
                swap(data,p,q);
                p = q;
            }
        }
    }

    private void circleSwap(int p){
        if((p>>>1) < 1){
            return;
        }

        if(data[p>>>1] < data[p]){
            swap(data,p>>>1,p);
            circleSwap(p >>> 1);
        }
    }

    private void swap(int[] a, int p, int q){
        int t = a[p];
        data[p] = data[q];
        data[q] = t;
    }

    public void show(){
        if (length > 1){
            for (int i = 1; i <= length; i++){
                System.out.print(data[i]);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Heap heap = new Heap(20);
        heap.put(1);
        heap.put(2);
        heap.put(3);
        heap.put(4);
        heap.put(5);
        heap.put(6);
        heap.put(7);
        heap.put(8);
        heap.put(9);
        heap.put(10);
        heap.show();
        System.out.println("removeHead");
        heap.removeHead();
        heap.show();
    }
}
