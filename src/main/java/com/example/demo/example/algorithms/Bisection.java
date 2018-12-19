package com.example.demo.example.algorithms;

/**
 * @author chen qi
 * 查找第一个等于给定值的元素
 * 查找最后一个等于给定值的元素
 * 查找第一个大于等于给定值的元素
 * 查找最后一个小于等于给定值的元素
 */
public class Bisection {
    /**
     * 查找第一个等于给定值的元素
     */
    private static int findFristEqual(int[] a, int k){
        int n = a.length;
        int low = 0;
        int high = n-1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] < k){
                low = mid + 1;
            }else if(a[mid] > k){
                high = mid - 1;
            }else{
                if(mid == 0 || a[mid - 1] != k){
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于给定值的元素
     */
    private static int findLastEqual(int[] a, int k){
        int n = a.length;
        int low = 0;
        int high = n-1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(a[mid] < k){
                low = mid + 1;
            }else if(a[mid] > k){
                high = mid -1;
            }else{
                if(mid == n-1 || a[mid + 1] != k){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     *查找第一个大于等于给定值的元素
     */
    private static int findFirstMoreOrEqual(int[] a, int k){
        int n = a.length;
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] < k){
                low = mid + 1;
            }else{
                if(mid == 0 || a[mid -1] < k){
                    return mid;
                }else{
                    high = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     */
    private static int findLastSmallOrEqual(int[] a, int k){
        int n = a.length;
        int low = 0;
        int high = n-1;

        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if (a[mid] > k){
                high = mid - 1;
            }else{
                if(mid == n-1 || a[mid + 1] > k){
                    return mid;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        //查找第一个等于给定值的元素
        int[] a1 = {0,1,2,4,4,4,5,5,5,6,7,9};
        int index1 = findFristEqual(a1,4);
        int index11 = findFristEqual(a1,5);
        System.out.println("4的位置：" + index1);
        System.out.println("5的位置：" + index11);
        //end------------------------------------
        // 查找最后一个等于给定值的元素
        int index2 = findLastEqual(a1, 4);
        int index22 = findLastEqual(a1, 5);
        System.out.println("最后一个4的位置:" + index2);
        System.out.println("最后一个5的位置:" + index22);
        //end-------------------------------------------
        // 查找第一个大于等于给定值的元素
        int index3 = findFirstMoreOrEqual(a1, 8);
        System.out.println("第一个大于等于8的位置是：" + index3);
        //end--------------------------------------------
        // 查找最后一个小于等于给定值的元素
        int index4 = findLastSmallOrEqual(a1, 9);
        System.out.println("最后一个小于等于9的元素：" + index4);
    }
}
