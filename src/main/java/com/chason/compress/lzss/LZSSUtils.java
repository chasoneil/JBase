package com.chason.compress.lzss;

import java.util.ArrayList;
import java.util.List;

public class LZSSUtils {

    private static final int WINDOW_SIZE = 4096;
    private static final int LOOKAHEAD_BUFFER_SIZE = 18;

    public static List<Object> compress(String input) {
        int inputSize = input.length();
        List<Object> compressed = new ArrayList<>();
        int currentPosition = 0;

        while (currentPosition < inputSize) {
            int matchLength = 0;
            int matchDistance = 0;
            int maxLength = Math.min(LOOKAHEAD_BUFFER_SIZE, inputSize - currentPosition);

            for (int j = Math.max(0, currentPosition - WINDOW_SIZE); j < currentPosition; j++) {
                int length = 0;
                while (length < maxLength && input.charAt(j + length) == input.charAt(currentPosition + length)) {
                    length++;
                }
                if (length > matchLength) {
                    matchDistance = currentPosition - j;
                    matchLength = length;
                }
            }

            if (matchLength >= 3) {
                compressed.add(new int[]{matchDistance, matchLength});
                currentPosition += matchLength;
            } else {
                compressed.add(input.charAt(currentPosition));
                currentPosition++;
            }
        }
        return compressed;
    }

    public static String decompress(List<Object> compressed) {
        StringBuilder output = new StringBuilder();
        int i = 0;

        while (i < compressed.size()) {
            Object element = compressed.get(i);
            if (element instanceof Character) {
                output.append((char) element);
                i++;
            } else if (element instanceof int[]) {
                int[] pair = (int[]) element;
                int matchDistance = pair[0];
                int matchLength = pair[1];
                int start = output.length() - matchDistance;

                for (int j = 0; j < matchLength; j++) {
                    output.append(output.charAt(start + j));
                }
                i++;
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        String input = "ABABABAABBBABAABBBBBAAA";
        List<Object> compressed = compress(input);
        System.out.println("Compressed: " + compressed);
        String decompressed = decompress(compressed);
        System.out.println("Decompressed: " + decompressed);
    }

}
