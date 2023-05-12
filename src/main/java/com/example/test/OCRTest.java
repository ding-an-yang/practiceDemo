package com.example.test;

import javax.annotation.PostConstruct;
import java.awt.geom.Point2D;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class OCRTest {

    public static void main(String[] args) {
        // 定义需要排序的数组
        int[] arr = {5, 3, 8, 2, 7, 1, 4, 6};
        // 输出原始数组
        System.out.println("原始数组：" + Arrays.toString(arr));
        // 冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 交换相邻的元素
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        Set<String> set = new HashSet<>();
        // 输出排序后的数组
        System.out.println("排序后的数组：" + Arrays.toString(arr));

    }

    private static double calc(List<Integer> ints) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.get();
        //临时中间集合
        List<Point2D> point2DList = new ArrayList<>();
        for (Integer i : ints) {
            point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
        }
        //临时变量，纯粹是为了获得最后结果需要的中间变量
        double total = 0;
        int count = 0;

        for (Point2D point2D : point2DList) {
            //过滤
            if (point2D.getY() > 1) {
                //算距离
                double distance = point2D.distance(0, 0);
                total += distance;
                count++;
            }
        }
        //注意count可能为0的可能
        return count > 0 ? total / count : 0;
    }

}
