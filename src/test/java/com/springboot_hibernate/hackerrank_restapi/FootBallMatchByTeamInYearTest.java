package com.springboot_hibernate.hackerrank_restapi;

import com.springboot_hibernate.entity.FootBallMatch;
import com.springboot_hibernate.repository.FootBallMatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FootBallMatchByTeamInYearTest {


    @Autowired  FootBallMatchByTeamInYear footBallMatchByTeamInYear;
    @Autowired
    FootBallMatchRepository footBallMatchRepository;
    @Test
    public void testMatchedGoals()  {
        int totalGoals = 0;
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team2");
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team1");
        System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Barcelona"));
        FootBallMatch footBallMatch  = FootBallMatch.builder().team("Barcelona").year(2011).build();
        footBallMatchRepository.save(footBallMatch);
        totalGoals = 0;
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team2");
        totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team1");
        System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Chelsea"));

        footBallMatch  = new FootBallMatch("Chelsea", 2011, totalGoals, null);
        footBallMatchRepository.save(footBallMatch);
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
