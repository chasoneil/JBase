package com.chason.compress.deflate;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class DeflateUtils {

    /**
     * 压缩
     *
     * @param str 要压缩的字符串
     * @return 压缩后的字符串
     */
    public static String compress(String str) throws Exception{
        Deflater deflater = new Deflater(9); // 0 ~ 9 压缩等级 低到高 推荐9
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(256)){
            deflater.setInput(str.getBytes());
            deflater.finish();
            final byte[] bytes = new byte[256];
            while (!deflater.finished()) {
                int length = deflater.deflate(bytes);
                outputStream.write(bytes, 0, length);
            }
            return new Base64().encodeToString(outputStream.toByteArray());
        } catch (Exception e) {
            throw e;
        } finally {
            deflater.end();
        }
    }

    /**
     *
     * @param encodeStr 待解压缩的字符串
     * @return 解压缩后的字节数组
     * @throws IOException
     */
    public static String uncompress(String encodeStr) throws IOException {
        int len = 0;
        Inflater infl = new Inflater();
        infl.setInput(Base64.decodeBase64(encodeStr));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] outByte = new byte[1024];
        try {
            while (!infl.finished()) {
                // 解压缩并将解压缩后的内容输出到字节输出流bos中
                len = infl.inflate(outByte);
                if (len == 0) {
                    break;
                }
                bos.write(outByte, 0, len);
            }
            infl.end();
        } catch (Exception e) {
            //
        } finally {
            bos.close();
        }
        return new String(bos.toByteArray());
    }
    public static void main(String[] args)throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("{\"test\":\"111\"}");
        String str = sb.toString();
        String eos = compress(str);
        System.out.println(eos);
        String deos = uncompress(eos);
        System.out.println(deos);
    }
}
