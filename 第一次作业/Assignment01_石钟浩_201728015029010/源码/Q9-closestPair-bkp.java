// http://acm.split.hdu.edu.cn/showproblem.php?pid=1007
// 题目要求实际上就是求最近点对的距离的一半

package com.szh;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
//import java.util.Scanner;

/**
 * User: Shi Zhonghao
 * Date: 2017-10-09
 * Time: 下午10:09
 */

class point {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

class XComparator implements Comparator<point> {
    @Override
    public int compare(point o1, point o2) {
        return (int) (o1.getX() - o2.getX());
    }
}

class YComparator implements Comparator<point> {
    @Override
    public int compare(point o1, point o2) {
        return (int) (o1.getY() - o2.getY());
    }
}

public class Main {

    private static point[] points;
    private static int MAX = Integer.MAX_VALUE;
    private static int n;

    public static void main(String[] args) throws Exception {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            n = (int) sc.nextInt();
//            if (0 == n)
//                break;
//            // 这句代码只是创建了一个长度为n的对象数组，里面的元素都还是空的！！！
//            points = new point[n];
//            for (int i = 0; i < n; i++) {
//                point p = new point();
//                p.setX(sc.nextDouble());
//                p.setY(sc.nextDouble());
//                points[i] = p;
//            }
//            Arrays.sort(points, new XComparator());
//            System.out.println(String.format("%.2f", closestPairDis(0, n-1) / 2).toString());
//        }
        while (st.nextToken() != StreamTokenizer.TT_EOF) {
            n = (int) st.nval;
            if (0 == n)
                break;
            // 这句代码只是创建了一个长度为n的对象数组，里面的元素都还是空的！！！
            points = new point[n];
            for (int i = 0; i < n; i++) {
                point p = new point();
                st.nextToken();
                p.setX(st.nval);
                st.nextToken();
                p.setY(st.nval);
                points[i] = p;
            }
            Arrays.sort(points, new XComparator());
            System.out.println(String.format("%.2f", closestPairDis(0, n-1)).toString());
        }
    }

    public static double dis(point p1, point p2) {
        return Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
    }

    public static double closestPairDis(int left, int right) {
        if (left == right)
            return MAX;
        if (left + 1 == right) {
            return dis(points[left], points[right]);
        }
        int mid;
        double left_dis, right_dis, min_dis;
        mid = left + (right - left) / 2;
        left_dis = closestPairDis(left, mid);
        right_dis = closestPairDis(mid + 1, right);
        min_dis = Math.min(left_dis, right_dis);
        point[] temp = new point[n];
        int j = 0;
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[i].getX() - points[mid].getX()) <= min_dis)
                temp[j++] = points[i];
        }
        Arrays.sort(temp, 0, j, new YComparator());
        for (int i = 0; i < j; i++) {
//            for (int k = i + 1; k < j && temp[k].getY() - temp[i].getY() < min_dis; k++) {
//                min_dis = Math.min(min_dis, dis(temp[i], temp[k]));
//            }
            for (int k = i + 1, m = 0; k < j && m < 11; k++) {
                min_dis = Math.min(min_dis, dis(temp[i], temp[k]));
                m++;
            }
        }
        return min_dis;
    }
}


























