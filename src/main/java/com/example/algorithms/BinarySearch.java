package com.example.algorithms;

import org.apache.http.annotation.Contract;

import javax.validation.constraints.NotNull;

/**
 * @author ：yangan
 * @date ：2023/3/21 下午4:08
 * @description：二分查找算法的实现相关代码
 * @version:
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,8,8,8,8,9,10,11,12,13,14};
        int first = binarySearchFirst(a, 19);
        int last = binarySearchLast(a, 8);
        int big = binarySearchFirstBig(a, 13);
        System.out.println(big);



    }

    /**
     * 二分法查找 key  返回数组中的第一个元素小标
     * 如 int[] a = {1,2,3,4,5,8,8,8,10} key = 8
     * 查找返回 index = 5
     * @param a 目标数组
     * @param value 查找的元素
     * @return 返回索引
     */
    public static int binarySearchFirst(int[] a, int value){
        int low = 0;
        int high = a.length - 1;

        if (high < 1) return -1;
        while (low <= high){
            int middle = low + ((high - low) >> 1);
            if (a[middle] < value){
                low = middle + 1;
            }else if (a[middle] > value){
                high = middle - 1;
            }else {
                if ((middle == 0) || (a[middle - 1] != value)){
                    return middle;
                }else {
                    high = middle - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分法查找 key  返回数组中的最后一个元素小标
     * 如 int[] a = {1,2,3,4,5,8,8,8,10} key = 8
     * 查找返回 index = 7
     * @param a 目标数组
     * @param value 查找的元素
     * @return 返回索引
     */
    public static int binarySearchLast(int[] a, int value){
        int low = 0;
        int high = a.length - 1;

        if (high < 1) return -1;
        while (low <= high){
            int middle = low + ((high - low) >> 1);
            if (a[middle] < value){
                low = middle + 1;
            }else if (a[middle] > value){
                high = middle - 1;
            }else {
                if ((middle == 0) || (a[middle + 1] != value)){
                    return middle;
                }else {
                    low = middle + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 二分法查找 key  返回数组中第一个大于key的元素小标
     * 如 int[] a = {1,2,3,4,5,8,8,8,8,9,10,11,12,13,14} key = 10
     * 查找返回 index = 10
     * @param a 目标数组
     * @param value 查找的元素
     * @return 返回索引
     */
    public static int binarySearchFirstBig(int[] a, int value){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int middle = low + ((high - low) >> 1);
            if (a[middle] >= value){
                if ((middle == 0) || (a[middle - 1] < value)){
                    return middle;
                }else {
                    high = middle - 1;
                }
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }



}