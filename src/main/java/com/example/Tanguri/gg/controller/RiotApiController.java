package com.example.Tanguri.gg.controller;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import com.example.Tanguri.gg.domain.dto.MatchDTO;
import com.example.Tanguri.gg.domain.dto.SummonerDto;
import com.example.Tanguri.gg.service.RiotApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RiotApiController {
    private final RiotApiService riotApiService;

    @GetMapping("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    public AccountDto getAccountByRiotId(@PathVariable String gameName, @PathVariable String tagLine) {
        return riotApiService.getAccountByRiotId(gameName, tagLine);
    }
    @GetMapping("/lol/summoner/v4/summoners/by-puuid/{encryptedPUUID}")
    public SummonerDto getSummonerByPUUID(@PathVariable String encryptedPUUID) {
        return riotApiService.getSummonerByPUUID(encryptedPUUID);
    }
    @GetMapping("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    public List<String> getMatchIdsByPUUID(@PathVariable String puuid) {
        return riotApiService.getMatchIdsByPUUID(puuid);
    }
    @GetMapping("/lol/match/v5/matches/{matchId}")
    public Object getMatchById(@PathVariable String matchId) throws IOException {
        return riotApiService.getMatchById(matchId);
    }
}
