package com.daria.recipe.app.schedulers;

import com.daria.recipe.app.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenCleanupScheduler {

    private final RefreshTokenService refreshTokenService;

    @Scheduled(cron = "0 0 3 * * ?") // каждый день в 3 часа ночи
    public void cleanExpiredTokens() {
        refreshTokenService.cleanExpiredTokens();
    }
}