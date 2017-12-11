import com.hw2007.utils.BinaryStdIn;
import com.hw2007.utils.BinaryStdOut;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;

/**
 * User: Shi Zhonghao
 * Date: 2017-11-11
 * Time: 下午10:36
 */
public class Huffman {
    // alphabet size of extended ASCII
    private static final int R = 256;

    // don't instantiate 不实例化
    private Huffman() { }

    /**
     * Huffman trie node
     */
    private static class Node implements Comparable<Node> {
        private final char ch;  // 节点对应的字符
        private final int freq; // 字符出现的次数
        private final Node left, right;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        // is the node a leaf node ?
        private boolean isLeaf() {
            return (left == null) && (right == null);
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Build the Huffman Trie by given frequencies
     * @param freq
     * @return
     */
    private static Node buildTrie(int[] freq) {

        // initialize priority queue with singleton tree
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char i = 0; i < R; i++) {
            if (freq[i] > 0)
                pq.add(new Node(i, freq[i], null, null));
        }

        // 特殊情况, 只有一个字符出现次数大于0
        if (pq.size() == 1) {
            if (0 == freq['\0'])
                pq.add(new Node('\0', 0, null, null));
            else
                pq.add(new Node('\1', 0, null, null));
        }

        // merge two smallest trees
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }

    /**
     * make a lookup table from symbols and their encodings
     * @param st
     * @param x
     * @param s
     */
    private static void buildCode(String[] st, Node x, String s) {
        if (!x.isLeaf()) {
            buildCode(st, x.left, s + "0");
            buildCode(st, x.right, s + "1");
        }
        else
            st[x.ch] = s;
    }

    /**
     * 使用单词查找树构造编译表, 表中记录了每个字符对应的比特流编码字符串.
     * @param root
     * @return
     */
    private static String[] buildCode(Node root) {
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    /**
     * 将单词查找树编码为比特流
     * @param x
     */
    private static void writeTrie(Node x) {
        // 前序遍历
        if (x.isLeaf()) {
            BinaryStdOut.write(true);   // 如果为叶子节点, 写入一个比特1
            BinaryStdOut.write(x.ch, 8);
            return;
        }
        BinaryStdOut.write(false);      // 如果为内部节点, 写入一个比特0
        writeTrie(x.left);
        writeTrie(x.right);
    }

    /**
     * 从标准输入中读取字节流, 转换为 Huffman code 后用标准输出打印结果.
     */
    public static void compress() {
        // read the input
        String s  = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // 纪录每个字符出现的次数
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        // 构造 Huffman 单词查找树
        Node root = buildTrie(freq);

        // 根据单词查找树构造编译表
        String[] st = new String[R];
        st = buildCode(root);

        // 打印解码用的单词查找树
        writeTrie(root);

        // 打印字符总数
        BinaryStdOut.write(input.length);

        // 将输入转换为 Huffman code
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++) {
//                System.out.print(code.charAt(j));
                if ('1' == code.charAt(j))
                    BinaryStdOut.write(true);
                else if ('0' == code.charAt(j))
                    BinaryStdOut.write(false);
                else
                    throw new IllegalStateException("Illegal state");
            }
//            System.out.println();
        }

        // 关闭输出流
        BinaryStdOut.close();
    }

    /**
     * 从比特流中读取单词查找树.
     * @return
     */
    private static Node readTrie() {
        // 当前比特为1, 则为叶节点
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }

    /**
     * 对 Huffman 编码的比特流进行解码.
     */
    public static void uncompress() {
        // 从比特流中读取 Huffman 单词查找树
        Node root = readTrie();

        // 需要解码的字节总数
        int length = BinaryStdIn.readInt();

        // 利用单词查找树进行解码
        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();
                // 当前比特为1, 往右子树走, 否则往左子树走
                if (bit)
                    x = x.right;
                else
                    x = x.left;
            }
            BinaryStdOut.write(x.ch, 8);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) {
//            // Idea 中获取命令行参数
//            try {
//                FileInputStream input = new FileInputStream(args[1]);
//                System.setIn(input);
//            }
//            catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            compress();
        }
        else if (args[0].equals("+")) {
            uncompress();
        }
        else
            throw new IllegalArgumentException("Illegal command line argument");
    }
}




































