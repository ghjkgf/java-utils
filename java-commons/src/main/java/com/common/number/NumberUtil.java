package com.common.number;

/**
 * @date:2020/1/6 20:24
 * @author: <a href='mailto:fanhaodong516@qq.com'>Anthony</a>
 */
public final class NumberUtil {
    private NumberUtil() {
    }

    /**
     * short 转换为 byte数组
     * 0xff 是 1111,1111 ,因为一个字节是8bit ,
     *
     * <p>
     * &	与	两个位都为1时，结果才为1
     * <p>
     * |	或	两个位都为0时，结果才为0
     *
     * @param s short类型
     * @return byte数组
     */
    public static byte[] shortToByte(short s) {
        byte[] b = new byte[2];
        for (int i = 0; i < 2; ++i) {
            int offset = 16 - (i + 1) * 8; //因为byte占8个bit，所以要计算偏移量
            b[i] = (byte) ((s >> offset) & 0xff); //把16位分为2个字节进行分别存储
        }
        return b;
    }


    /**
     * byte数组 转换为 short
     *
     * @param b byte数组
     * @return short类型
     */
    public static short byteToShort(byte[] b) {
        short l = 0;
        for (int i = 0; i < 2; ++i) {
            l <<= 8; //  意思就是右移  l = l << 8
            l |= (b[i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
        }
        return l;
    }

    public static byte[] intToByte(int s) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; ++i) {
            int offset = 32 - (i + 1) * 8;
            b[i] = (byte) ((s >> offset) & 0xff);
        }
        return b;
    }


    public static int byteToInt(byte[] b) {
        int l = 0;
        for (int i = 0; i < 4; ++i) {
            l <<= 8;
            l |= (b[i] & 0xff);
        }
        return l;
    }


    public static byte[] longToByte(long s) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; ++i) {
            int offset = 64 - (i + 1) * 8;
            b[i] = (byte) ((s >> offset) & 0xff);
        }
        return b;
    }


    public static long byteToLong(byte[] b) {
        long l = 0;
        for (int i = 0; i < 8; ++i) {
            l <<= 8;
            l |= (b[i] & 0xff);
        }
        return l;
    }


    public static void showByte(byte[] b) {
        int x = 0;
        for (byte b1 : b) {
            System.out.printf("%d\t", b1);
            if (++x % 10 == 0) {
                System.out.println();
            }
        }
    }

    // 8 的原码
    // 0000 1000

    // 8的反码 , 全部取反
    // 1111 0111

    // 8的补码 = 8的反码+1
    // 1111 1000
    public static void main(String[] args) {
        int x = 1;

        byte[] bytes = intToByte(x);
        for (byte aByte : bytes) {
            System.out.printf("%d,", aByte);
        }
    }
}
