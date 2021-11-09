package com.example.myleecodedemo.leecode;


public class testSort {

    public static void main(String[] args) {
        int[]  arr = {3,5,7,4,6,8,9};
        heapSort(arr);
        System.out.println(arr);
    }
    //向上追溯 构建大顶堆
    public static void heapInsert(int[] arr,int index){
        while(arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }
    //向下追溯
    public static void heapify(int[] arr,int index,int heapSize){
        int left = index*2+1;   //左孩子的下标
        while(left<heapSize){
//两个孩子中较大的
            int largest  =left+1<heapSize&& arr[left+1]>arr[left]?left+1:left;
//父节点和较大的那个孩子节点比较。谁的值大，把下标给largest
            largest = arr[largest]>arr[index]?largest:index;

            if(largest==index){
                break;
            }
            swap(arr,largest,index);
            index = largest;
            left = index*2+1;
        }
    }
    //堆排序  //时间复杂度O(nlogN)，空间复杂度O(1)
    public static void heapSort(int[] arr){
        if(arr == null || arr.length <2){
            return;
        }
        for(int i=0;i<arr.length;i++){	//向上追溯。构建原始大顶堆    //时间复杂度O(N)
            heapInsert(arr,i);		//时间复杂度O(logN)
        }
//        for (int i = arr.length-1; i >=0 ; i--) {             //此方式亦可构建原始大顶堆。
//            heapify(arr,i,arr.length);
//        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);  //交换最顶端数和最后一个数的值，并且heapSize执行--操作
        while(heapSize>0){	   //把最顶端的值向下追溯，构建新的大顶堆。然后再进行首尾值交换，接着heapSize执行--操作   //时间复杂度O(N)
            heapify(arr,0,heapSize);    //时间复杂度O(logN)
            swap(arr,0,--heapSize);
        }
    }

    public static void swap(int[] arr,int i,int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

}
