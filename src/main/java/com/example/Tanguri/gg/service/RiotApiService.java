package com.example.Tanguri.gg.service;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import com.example.Tanguri.gg.domain.dto.SummonerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;
import java.util.List;

@Service
public class RiotApiService {
    @Value("${riot.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public AccountDto getAccountByRiotId(String gameName, String tagLine) {
        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/"
                + gameName + "/" + tagLine + "?api_key=" + apiKey;
        return restTemplate.getForObject(url, AccountDto.class);
    }

    public SummonerDto getSummonerByPUUID(String encryptedPUUID) {
        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + encryptedPUUID+
                "?api_key=" + apiKey;
        System.out.println("url = " + url);
        return restTemplate.getForObject(url, SummonerDto.class);
    }

    public List<String> getMatchIdsByPUUID(String puuid) {
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key="+apiKey;

        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // HttpHeaders 대신 null 사용
                new ParameterizedTypeReference<List<String>>() {}
        );

        return response.getBody();
    }
    public Object getMatchById(String matchId) {
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;

        ResponseEntity<Object> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null, // No additional headers needed
                Object.class
        );

        return response.getBody();
    }
}
