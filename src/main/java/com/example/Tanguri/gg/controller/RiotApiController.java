package com.example.Tanguri.gg.controller;

import com.example.Tanguri.gg.domain.dto.AccountDto;
import com.example.Tanguri.gg.service.RiotApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RiotApiController {
    private final RiotApiService riotApiService;

    @GetMapping("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
    public AccountDto getAccountByRiotId(@PathVariable String gameName, @PathVariable String tagLine) {
        return riotApiService.getAccountByRiotId(gameName, tagLine);
    }
}
