package com.example.Tanguri.gg.service;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import com.example.Tanguri.gg.domain.dto.MatchDTO;
import com.example.Tanguri.gg.domain.dto.SummonerDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.Map;

@Service
public class RiotApiService {
    @Value("${riot.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

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
    public Object getMatchById(String matchId) throws IOException {
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;

//        Object forObject = restTemplate.getForObject(url, Object.class);


        // Make the API call
        Object response = restTemplate.getForObject(url, Object.class);

        // Convert the response to a Map
        Map<String, Object> responseMap = objectMapper.convertValue(response, Map.class);

        // Convert the Map to a JSON string
        String jsonString = objectMapper.writeValueAsString(responseMap);

        // Convert the JSON string to MatchDTO
        MatchDTO matchDTO = objectMapper.readValue(jsonString, MatchDTO.class);
        List<String> participants = matchDTO.getMetadata().getParticipants();

        return matchDTO;
    }
}
