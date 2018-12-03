package com.example.demo.util;

/**
 *  数组的插入、删除、按照下标随机访问操作；
 * @author chen qi
 */
public class Array {
    int[] data;
    /**定义数组长度*/
    private int n;
    /**定义中实际个数*/
    private int count;

    public Array(int capacity){
        data = new int[capacity];
        n = capacity;
        count = 0;
    }

    /**
     * 根据索引，找到数据中的元素并返回
     * @param index 位置
     * @return int
     */
    public int findIndex(int index){
        if(index < 0 || index >= count){
            return -1;
        }
        return data[index];
    }

    /**
     * 插入元素:头部插入，尾部插入
     * @param index 位置
     * @param value 值
     * @return boolean
     */
    public boolean insert(int index, int value){

        if(count == n){
            System.out.println("空间以满");
            return false;
        }

        if(index < 0 || index > count){
            System.out.println("位置不正确");
            return false;
        }

        for(int i = count; i > index; --i){
            data[i] = data[i-1];
        }
        data[index] = value;
        ++count;
        return true;
    }

    /**
     * 根据索引，删除数组中元素
     * @param index 位置
     * @return boolean
     */
    public boolean delete(int index){

        if(index < 0 || index >= count){
            System.out.println("无此位置");
            return false;
        }

        for(int i = index + 1; i < count; ++i){
            data[i-1] = data[i];
        }
        --count;
        return true;
    }
    public void printAll(){
        for(int i : data){
            System.out.println(i);
        }
    }

    public static void main(String[] args) {

    }
}
