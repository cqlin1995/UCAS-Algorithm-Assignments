package com.szh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by Shi Zhonghao on 17-10-5.
 */

public class Main {

    final static int LEN = 100000;

    private static long count = 0;
    private static int[] array = new int[LEN];
    private static int[] tempArr = new int[LEN];

    public static void main(String[] args) {
        // 读取Q8.txt中的数据存入array数组中，这里使用了文件的相对路径
        try {
            String pathName = "Q8.txt";
            File fileName = new File(pathName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
            BufferedReader buf = new BufferedReader(reader);
            String line;
            int i = 0;
            while ((line = buf.readLine()) != null) {
                // System.out.println(line);
                array[i++] = Integer.parseInt(line);
            }
            count = 0;
            mergeSort(0, array.length - 1);
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 归并排序的递归调用
    public static void mergeSort(int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(low, mid);
        mergeSort(mid + 1, high);
        mergeAndSort(low, mid, high);
    }

    // 将两个升序排列的数组合并成一个升序排列的数组，并计算两个数组直接首尾拼接的情况下包含多少个逆序对
    public static void mergeAndSort(int low, int mid, int high) {
        int temp = low;
        int idx = low;
        int center = mid + 1;
        while (low <= mid && center <= high) {
            if (array[low] <= array[center])
                tempArr[idx++] = array[low++];
            else {
                tempArr[idx++] = array[center++];
                count += mid - low + 1; // 如果array[low]大于array[center]，则[low,mid]这个区间内的元素都大于array[center]
            }
        }
        while (low <= mid) {
            tempArr[idx++] = array[low++];
        }
        while (center <= high) {
            tempArr[idx++] = array[center++];
        }
        while (temp <= high) {
            array[temp] = tempArr[temp++];
        }
    }
}


