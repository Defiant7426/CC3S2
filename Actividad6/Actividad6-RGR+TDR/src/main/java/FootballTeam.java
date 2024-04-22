public class  FootballTeam implements  Comparable<FootballTeam>{
    private int gamesWon;
    public FootballTeam(int gamesWon) {
        if(gamesWon<0){
            throw new IllegalArgumentException(
                    "Not posibleto have less than 0 games won! + (was +" + gamesWon + ")");
        }
        this.gamesWon = gamesWon;
    }
    public int getGamesWon() {
        //return 0;
        return gamesWon;
    }
    @Override
    public int compareTo(FootballTeam otherTeam) {
        return gamesWon - otherTeam.getGamesWon();
    }




}


