package com.springboot_hibernate;

import com.springboot_hibernate.entity.FootBallMatch;
import com.springboot_hibernate.hackerrank_restapi.FootBallMatchByTeamInYear;
import com.springboot_hibernate.repository.FootBallMatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringHiberateApplication  implements CommandLineRunner {

    @Autowired FootBallMatchByTeamInYear footBallMatchByTeamInYear;
	@Autowired
	FootBallMatchRepository footBallMatchRepository;


	private Logger log = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		SpringApplication.run(SpringHiberateApplication.class, args);

	}

	@Override
	public void run(String... args) throws IOException {
		// testMatchedGoals();
	}

	private void testMatchedGoals()  {
		int totalGoals = 0;
		totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team2");
		totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Barcelona", 2011, "team1");
		System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Barcelona"));
		FootBallMatch footBallMatch = new FootBallMatch("Barcelona", 2011, totalGoals, null);
		footBallMatchRepository.save(footBallMatch);
		totalGoals = 0;
		totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team2");
		totalGoals+= footBallMatchByTeamInYear.retrieveTotalGoalsInYear("Chelsea", 2011, "team1");
		System.out.println(String.format("total goals: %s in %s of %s", totalGoals, 2011, "Chelsea"));
		footBallMatch = new FootBallMatch("Chelsea", 2011, totalGoals, null);
		footBallMatchRepository.save(footBallMatch);
	}
}
