package com.hw2007.utils;

/**
 * User: Shi Zhonghao
 * Date: 2017-11-12
 * Time: 下午4:34
 */

public class BinaryDump {

    // Do not instantiate.
    private BinaryDump() { }

    /**
     * 从 standard input 读取字节流, 输出比特流.
     * @param args
     */
    public static void main(String[] args) {
        int bitsPerLine = 16;
        if (args.length == 1) {
            bitsPerLine = Integer.parseInt(args[0]);
        }

        int count;
        for (count = 0; !BinaryStdIn.isEmpty(); count++) {
            if (bitsPerLine == 0) {
                BinaryStdIn.readBoolean();
                continue;
            }
            else if (count != 0 && count % bitsPerLine == 0)
                System.out.println();
            if (BinaryStdIn.readBoolean())
                System.out.print(1);
            else
                System.out.print(0);
        }
        if (bitsPerLine != 0)
            System.out.println();
        System.out.println(count + " bits");
    }
}