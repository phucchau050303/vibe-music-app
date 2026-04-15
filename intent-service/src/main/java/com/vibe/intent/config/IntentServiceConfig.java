package com.vibe.intent.config;

import com.center.common.dto.MusicQuery;
import com.vibe.intent.functions.VibeProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class IntentServiceConfig {
    @Bean
    public Function<String, MusicQuery> processIntent(VibeProcessor vibeProcessor){
        return vibeProcessor::process;
    }
}
