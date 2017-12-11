package com.hw2007.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;

/**
 * User: Shi Zhonghao
 * Date: 2017-11-11
 * Time: 下午9:04
 */
public final class BinaryStdOut {
    private static BufferedOutputStream out = new BufferedOutputStream(System.out);

    private static int buffer;  // 8-bit buffer of bits to write out
    private static int n;       // number of bits left in buffer

    // don't instantiate 不实例化
    private BinaryStdOut() { }

    /**
     * Write out any remaining bits in buffer to standard output, padding with 0s
     */
    private static void clearBuffer() {
        if (0 == n)
            return;
        if (n > 0)
            buffer <<= (8 - n);
        try {
            out.write(buffer);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        n = 0;
        buffer = 0;
    }

    /**
     * Write the specified bit to standard output.
     * @param bit
     */
    private static void writeBit(boolean bit) {
        // add bit to buffer
        buffer <<= 1;
        if (bit)
            buffer |= 1;

        // if buffer is full (8-bits), write out as a single byte
        n++;
        if (8 == n)
            clearBuffer();
    }

    /**
     * write the 8-bit byte to standard output.
     * @param x
     */
    private static void writeByte(int x) {
        assert x >= 0 && x < 256;   // 断言必须为 true

        // buffer 为空, 即当前是字节对齐的
        if (0 == n) {
            try {
                out.write(x);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        // buffer 不为空, 每次写入 1 bit
        for (int i = 0; i < 8; i++) {
            boolean bit = ((x >>> (8 - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }

    /**
     * Flush standard output, padding 0s if number of bits written so far
     * is not a multiple of 8.
     */
    public static void flush() {
        clearBuffer();
        try {
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Flush and close standard output.
     */
    public static void close() {
        flush();
        try {
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write the specified bit to standard output.
     * @param x
     */
    public static void write(boolean x) {
        writeBit(x);
    }

    /**
     * Write the 32-bit int to standard output.
     * @param x
     */
    public static void write(int x) {
        writeByte((x >>> 24) & 0xff);
        writeByte((x >>> 16) & 0xff);
        writeByte((x >>> 8) & 0xff);
        writeByte((x >>> 0) & 0xff);
    }

    /**
     * Write the 8-bit char to standard output.
     * @param x
     */
    public static void write(char x) {
        if (x < 0 || x >= 256)
            throw new IllegalArgumentException("Illegal 8-bit char = " + x);
        writeByte(x);
    }

    /**
     * Write r-bit char to standard output
     * @param x
     * @param r
     */
    public static void write(char x, int r) {
        if (8 == r) {
            write(x);
            return;
        }
        if (r < 1 || r > 16)
            throw new IllegalArgumentException("Illegal value for r = " + r);
        if (x >= (1 << r))
            throw new IllegalArgumentException("Illegle " + r + "-bit char = " + x);
        for (int i = 0; i < r; i++) {
            boolean bit = ((x >>> (r - i - 1)) & 1) == 1;
            writeBit(bit);
        }
    }
}
































