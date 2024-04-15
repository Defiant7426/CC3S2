package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class WordTest {
    @Test
    void oneIncorrectLetterShoudIncorrect(){
        //Arrange
        var word = new Word("A");

        var score = word.guess("Z");

        //Act
        var result = score.letter(0);

        //Assert
        assertThat(result).isEqualTo(Letter.INCORRECT);
    }

    @Test
    void oneCorrectLetterShoudCorrect(){
        //Arrange
        var word = new Word("A");

        var score = word.guess("A");

        //Act
        var result = score.letter(0);

        //Assert
        assertThat(result).isEqualTo(Letter.CORRECT);
    }

    @Test
    void oneWordCorrectButPositionIncorrectShoudPartCorrect(){
        //Arrange
        var word = new Word("amigo");

        var score = word.guess("m");

        //Act
        var result = score.letter(0);

        //Assert
        assertThat(result).isEqualTo(Letter.PART_CORRECT);
    }

    @Test
    void guessIsEmptyShoudThrowForInvalidArgument(){
        //Arrange, Act and Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{
                    new Word("A").guess("");
                });
    }

    @Test
    void wordContructorIsEmptyShoudThrowForInvalidArgument(){
        //Arrange, Act and Assert
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Word("");
                });
    }

}
