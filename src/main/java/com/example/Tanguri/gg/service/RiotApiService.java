package com.example.Tanguri.gg.service;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
