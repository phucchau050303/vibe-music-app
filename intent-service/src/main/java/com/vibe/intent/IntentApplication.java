package com.vibe.intent;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import com.vibe.intent.functions.VibeProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class IntentApplication {
    private final VibeProcessor vibeProcessor = new VibeProcessor("");

    public static void main(String[] args){

        SpringApplication.run(IntentApplication.class, args);
    }


    @Bean
    public CommandLineRunner run(VibeProcessor vibeProcessor) {
        return args -> {
            System.out.println("--- STARTING STARTUP TEST ---");
            try {
                vibeProcessor.process("Lofi hip hop for studying");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        };
    }
}