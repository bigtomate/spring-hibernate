package com.springboot_hibernate.hackerrank_restapi;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;


@Service
public class FootBallMatchByTeamInYear {


    private final RestClient.Builder restClientBuilder;
    private final RestClient restClient;
    public FootBallMatchByTeamInYear(RestClient.Builder restClientBuilder) {
        this.restClientBuilder = restClientBuilder;
        this.restClient = createRestClient("https://jsonmock.hackerrank.com/api", "");
    }


    public int retrieveTotalGoalsInYear(String team, int year, String teamNr) {
        int goals = 0;
        int currentPage = 1;

        while (true) {

            String teamUrlFormat = "/football_matches?year=%s&%s=%s&page=%s";
            String urlStr = String.format(teamUrlFormat, year, teamNr, team, currentPage);
            FootBallApiResponse apiResponse =  restClient.get()
                    .uri(urlStr)
                    .retrieve()
                    .body(FootBallApiResponse.class);

            int totalPage = apiResponse.totalPages;

            for (FootBallResult match : apiResponse.data) {
             if (teamNr.equals("team2")) {
                 goals += match.team2goals;
             }
             else if (teamNr.equals("team1")) {
                    goals += match.team1goals;
                }
            }

            if (currentPage >= totalPage) {
                break;
            }
            currentPage++;
        }

        return goals;
    }

    public int retrieveDraws(int year) {
        int draws = 0;
        int countDraws = 0;
        while (true) {
                String urlStr = String.format(
                        "/football_matches?year=%d&team1goals=%d&team2goals=%d",
                        year, countDraws, countDraws
                );

            FootBallApiResponse apiResponse =  restClient.get()
                    .uri(urlStr)
                    .retrieve()
                    .body(FootBallApiResponse.class);

            draws += apiResponse.total;

            if (countDraws > 9) {
                break;
            }
            countDraws++;
        }

        return draws;
    }
    private RestClient createRestClient(String baseUrl, String bearerToken) {
        return restClientBuilder
                .baseUrl(baseUrl)
                .requestInterceptor((request, body, execution) -> {
                    if (bearerToken != null && !bearerToken.isEmpty()) {
                        request.getHeaders().setBearerAuth(bearerToken);
                    }
                    return execution.execute(request, body);
                })
                .build();
    }
}
