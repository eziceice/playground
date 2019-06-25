package com.springboot.bet.config;

import com.springboot.bet.logger.BetControllerLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AspectConfig {
    @Bean(name = "betControllerLogger")
    public BetControllerLogger initBetControllerLogger() {
        return new BetControllerLogger();
    }
}
