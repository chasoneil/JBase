package com.chason.ocjp.practice;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Practice01 {


    public static void main(String[] args) throws Exception {

        demo01();

    }

    public static int sum(List list)  {

        int sum =0;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {

            int i = ((Integer) iterator.next()).intValue();
            sum += i;
        }

        var tmp = "a";

        return sum;
    }

    /**
     * JDK api 将文件一行行输出
     * @throws Exception
     */
    public static void demo01 () throws Exception {
        Path file = Path.of("D:/temp/a.txt");
        Files.lines(file, Charset.forName("GBK")).forEach(line -> System.out.println(line));

    }



}
