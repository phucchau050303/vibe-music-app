package com.vibe.intent;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;


public class IntentApplication {

    public static void main(String[] args){
        SpringApplication.run(IntentApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(@Value("${app.api.google.key}") String key) {
        return args -> {
            Client client = Client.builder().apiKey(key).build();

            GenerateContentResponse response =
                    client.models.generateContent(
                            "gemini-3-flash-preview",
                            "Explain how AI works in a few words",
                            null);
            System.out.println("GEMINI Response: -> ");
            System.out.println(response.text());
        };
    }
}