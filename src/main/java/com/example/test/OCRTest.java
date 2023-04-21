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
        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        double average = calc(ints);
        double streamResult = ints.stream()
                .map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
                .filter(point -> point.getY() > 1)
                .mapToDouble(point -> point.distance(0, 0))
                .average()
                .orElse(0);
        int value1 = 2;
        int value2 = 2;
        System.out.println(value1 == value2);

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


class Test {
        volatile String key = "1234";
}





}