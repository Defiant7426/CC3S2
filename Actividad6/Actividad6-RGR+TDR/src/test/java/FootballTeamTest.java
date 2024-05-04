import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.params.provider.ValueSource;
public class FootballTeamTest {

    private static final int ANY_NUMBER = 123;

    @ParameterizedTest
    @ValueSource(ints = {0,1,3,10})
    void constructorShouldSetGamesWon(int nbOfGamesWon) {
        FootballTeam team = new FootballTeam(nbOfGamesWon);
        assertThat(team.getGamesWon())
            .as("number of games won")
            .isEqualTo(nbOfGamesWon);
    }
    @ParameterizedTest
    @ValueSource(ints = {-10, -1})
    void constructorShouldThrowIllegalArgumentException(int nbOfGamesWon) {
        assertThatExceptionOfType(IllegalArgumentException.class)
            .isThrownBy(() -> new FootballTeam(nbOfGamesWon));
    }
    @Test
    void shouldBePossibleToCompareTeams() {
     FootballTeam team = new FootballTeam(ANY_NUMBER);
     assertThat(team).isInstanceOf(Comparable.class);
    }
    @Test
    void teamsWithMoreMatchesWonShouldBeGreater() {
         FootballTeam team_2 = new FootballTeam(2);
         FootballTeam team_3 = new FootballTeam(3);
         assertThat(team_3.compareTo(team_2)).isGreaterThan(0);
    }
    @Test
    void teamsWithLessMatchesWonShouldBeLesser() {
        FootballTeam team_2 = new FootballTeam(2);
        FootballTeam team_3 = new FootballTeam(3);
        assertThat(team_2.compareTo(team_3))
        .isLessThan(0);
    }
    @Test
    void teamsWithSameNumberOfMatchesWonShouldBeEqual() {
         FootballTeam teamA = new FootballTeam(2);
         FootballTeam teamB = new FootballTeam(2);
         assertThat(teamA.compareTo(teamB))
         .isEqualTo(0);
    }

}