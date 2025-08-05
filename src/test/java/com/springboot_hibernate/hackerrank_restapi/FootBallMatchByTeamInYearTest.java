package com.springboot_hibernate.hackerrank_restapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FootBallMatchByTeamInYearTest {


    @Autowired  FootBallMatchByTeamInYear footBallMatchByTeamInYear;

    @Test
    public void testMatchedGoals()  {
        int totalGoals = 0;
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team2");
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team1");
        System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Barcelona"));

        totalGoals = 0;
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team2");
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team1");
        System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Chelsea"));
    }

    @Test
    public void testDraw() {
        long beginn = System.currentTimeMillis();
        int totalDraws = 0;

            totalDraws = footBallMatchByTeamInYear.retrieveDraws(2011);

        System.out.println(
                String.format("total draws: %s in %s ",totalDraws, 2011)
        );
        System.out.println((float) (System.currentTimeMillis() - beginn) / 1000);
    }
}
