package com.wordz.domain;

import com.wordz.domain.Letter;
import com.wordz.domain.Score;
import com.wordz.domain.Word;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class WordTest {
    @Test
    void oneIncorrectLetter(){
        var word = new Word("A");
        var score = word.guess("Z");
        assertScoreForGuess(score, List.of(Letter.INCORRECT));
    }
    @Test
    void oneCorrectLetter(){
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

