package com.wordz.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WordTest {
    @Test
    public void oneIncorrectLetter(){
        var word = new Word("A");
        var score = word.guess("Z");
        assertScoreForGuess(score, List.of(Letter.INCORRECT));
    }
    @Test
    public void oneCorrectLetter(){
        var word = new Word("A");
        var score = word.guess("A");
        assertScoreForGuess(score, List.of(Letter.CORRECT));
    }
    @Test
    void secondLetterWrongPosition(){
        var word = new Word("AR");
        var score = word.guess("ZA");
        assertScoreForGuess(score, List.of(Letter.INCORRECT,
                Letter.PART_CORRECT));
    }
    @Test
    void allScoreCombinations(){
        var word = new Word("ARI");
        var score = word.guess("ZAI");
        assertScoreForGuess(score, List.of(Letter.INCORRECT,
                Letter.PART_CORRECT,
                Letter.CORRECT));
    }
    private void assertScoreForGuess(Score score,
                                     List<Letter> expectedScores){
        for (int position = 0;
             position < expectedScores.size();
             position++) {
            Letter expected = expectedScores.get(position);
            assertThat(score.letter(position)).isEqualTo(expected);
        }
    }
}

