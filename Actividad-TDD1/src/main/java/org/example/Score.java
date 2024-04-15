package org.example;

public class Score {
    private String correctWord;
    private String attempt;

    public Score(String correctWord, String attempt) {
        this.correctWord = correctWord;
        this.attempt = attempt;
    }

    public Letter letter(int position){
        if(attempt.isEmpty()) return Letter.INCORRECT;
        else if(this.correctWord.indexOf(attempt) == -1) return Letter.INCORRECT;
        else if(this.correctWord.indexOf(attempt) == position) return Letter.CORRECT;
        else return Letter.PART_CORRECT;
    }
    public String getCorrectWord() {
        return correctWord;
    }

    public String getAttempt() {
        return attempt;
    }

    public void setAttempt(String attempt) {
        this.attempt = attempt;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }
}
