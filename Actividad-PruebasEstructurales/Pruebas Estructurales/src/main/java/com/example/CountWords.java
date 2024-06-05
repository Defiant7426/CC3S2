package com.example;

public class CountWords {
    public int count(String str) {
        String[] words = str.split("\\s+");
        int count = 0;
        for (String word : words) {
            if (word.endsWith("s") || word.endsWith("r")) {
                count++;
            }
        }
        return count;
    }
}