package com.chason.algorithm.class09;

/**
 * 给定一个字符串，只能由 X 和 . 组成
 * X表示墙，不能放灯，也不能点亮
 * .表示居民点，可以点亮
 * 如果将灯放在i位置，可以让 i-1 i+1 i 三个位置被点亮
 * 如果需要点亮str中的所有位置，最少需要多少个灯
 */
public class _05_LowestLight {

    /*
    贪心策略： 来到i位置
    1. 如果i位置是X 那肯定不能放灯，直接去看i+1
    2. 如果i位置是.那看i+1位置
        如果i+1位置是X, 则i位置必须放灯，(灯++) 然后去看i+2
        如果i+1位置是. 那看i+2位置
            如果i+2位置是. 那么 灯放在i+1  (灯++)    看i+3
            如果i+2位置是X 那么灯放i i+1 都行(灯++)  看i+3
     */
    public static int lowestLight1(String str) {

        if (str == null || " ".equals(str)) {
            return 0;
        }

        char[] chs = str.toCharArray();

        int light = 0;
        int index = 0; // 当前遍历到的位置
        while (index < chs.length) {

            if (chs[index] == 'X') {
                index++;
            } else {  // i 位置是 .
                light++;
                if (index + 1 == chs.length) { // index 位置已经是数组末尾
                    break;
                } else {
                    if (chs[index+1] == 'X') {
                        index += 2;
                    } else {
                        if (index + 2 == chs.length) {
                            break;
                        } else {
                            index += 3;
                        }
                    }
                }
            }
        }

        return light;
    }

}
