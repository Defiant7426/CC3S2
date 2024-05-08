package com.wordz.domain;

public class Word {

    private final String w;

    public Word(String correctWord) {
        this.w = correctWord;
    }

    public Score guess(String attempt) {
        var score = new Score(w);
        score.assess(attempt);
        return score;
    }
}
