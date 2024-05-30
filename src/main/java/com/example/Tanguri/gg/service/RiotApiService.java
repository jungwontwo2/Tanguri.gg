package com.example.Tanguri.gg.service;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import com.example.Tanguri.gg.domain.dto.SummonerDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

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
}
