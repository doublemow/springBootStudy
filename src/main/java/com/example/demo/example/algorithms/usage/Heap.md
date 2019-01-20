##Heap
"堆"是一种特殊的树，只要满足一下两点他就是一个"堆"：

- 堆是一个完全二叉树；
- 堆中每一个节点的值都必须大于等于（或小于等于）其子树中每个节点的值；

"堆排序"是一种原地的、时间复杂度为O(nlogn)的排序算法。

###实现一个堆

实现一个堆要考虑以下两点：

1. 如何存储堆；
2. 堆都支持什么操作；

堆使用数组进行存储为最优方式，相对于链表方式可以节省出存储左右节点指针的空间。可以通过数组下标寻找左右子节点和父节点。

当树的根节点从数组下标为1的位置存储时，查找公式如下：

- 左节点位置 = 2 * i (i为当前位置)
- 右节点位置 = 2 * i + 1
- 父节点位置 = i / 2

1.插入功能

往堆中插入一个元素后，可能会违反堆的特性！所以需要重新进行调整使其重新满足堆的特性。我们把调整的过程叫做堆化（heapify）

堆化的方式由两种，从上向下和从下向上，下面代码为从下向上的方式：

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
            change(length);
    
            return true;
        }
    
        private void change(int p){
            if((p>>>1) < 1){
                return;
            }
    
            if(data[p>>>1] < data[p]){
                int t = data[p>>>1];
                data[p>>>1] = data[p];
                data[p] = t;
                change(p >>> 1);
            }
        }
    }

2、删除堆顶元素

将最后一个节点放在堆顶，然后利用同样的父节点对比方法，对于不满足父子节点大小关系的进行位置互换，重复此过程直到满足"堆"的规则，此方法叫做从上向下的堆化方法。


