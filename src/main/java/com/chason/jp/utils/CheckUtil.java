package com.chason.jp.utils;

public class CheckUtil {


    public static int checkInput(String input) {

        int selectNumber = -1;
        try {
            selectNumber = Integer.parseInt(input);
        } catch (Exception e) {
            System.out.println("您只能输入提示的选项数字，然后按下回车");
            System.out.println("本次练习结束，请重新开始运行练习程序。");
            System.exit(-1);
        }

        return selectNumber;
    }

    /**
     * 检查行是否有效，排除注释行和空行
     * @param line
     * @return
     */
    public static boolean checkLine (String line) {

        if (line == null || line.trim().isEmpty()) {
            return false;
        }

        if (line.trim().equals(" ") || line.trim().equals("\t")) {
            return false;
        }

        if (line.startsWith("#")) {
            return false;
        }

        return true;
    }
}
