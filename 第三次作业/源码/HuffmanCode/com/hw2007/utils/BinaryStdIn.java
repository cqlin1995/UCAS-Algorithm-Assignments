package com.hw2007.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * User: Shi Zhonghao
 * Date: 2017-11-11
 * Time: 下午5:33
 */
public final class BinaryStdIn {
    private static BufferedInputStream in = new BufferedInputStream(System.in);
    private static final int EOF = -1;  // end of file

    private static int buffer;  // one character buffer
    private static int n;       // number of bits left in buffer

    // static initializer 静态初始化器
    static {
        fillBuffer();
    }

    // don't instantiate 不实例化
    private BinaryStdIn() { }

    private static void fillBuffer() {
        try {
            buffer = in.read();
            n = 8;
        }
        catch (IOException e) {
            System.out.println("EOF");
            buffer = EOF;
            n = -1;
        }
    }

    /**
     * 如果 standard input 为空, return true
     * @return
     */
    public static boolean isEmpty() {
        return buffer == EOF;
    }

    /**
     * 读取 standard input 中的下一个 bit, bit为1则 return true
     * @return
     */
    public static boolean readBoolean() {
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");
        n--;
        boolean bit = ((buffer >> n) & 1) == 1;
        if (0 == n)
            fillBuffer();
        return bit;
    }

    /**
     * 从 standard input 中读入 8 bit 作为一个 8-bit char
     * @return
     */
    public static char readChar() {
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");
        // n = 8, 不需要与后面的输入流组成一个 8-bit char, 直接返回.
        // 正好为最后一个字符时, 后面没有输入流, 直接返回, 不然在下面代码调用 fillBuffer()时, 会抛出异常.
        if (8 == n) {
            int x = buffer;
            fillBuffer();
            return (char) (x & 0xff);
        }

        // 将当前 buffer 中剩余的 n 个 bit 与新读入的 buffer 的前 8-n 个 bit 组合
        int x = buffer;
        x <<= (8 - n);
        int oldN = n;
        fillBuffer();
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");
        n = oldN;
        x |= (buffer >>> n);
        return (char) (x & 0xff);
    }

    /**
     * 将 standard input 中剩余的字节作为一个字符串返回
     * @return
     */
    public static String readString() {
        if (isEmpty())
            throw new NoSuchElementException("Reading from empty input stream");

        StringBuilder sb = new StringBuilder();
        while (! isEmpty()) {
            char c = readChar();
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * 从 standard input 中读入 32 bits 作为一个 32-bit int 返回
     * @return
     */
    public static int readInt() {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            char c = readChar();
            x <<= 8;
            x |= c;
        }
        return x;
    }
}

























