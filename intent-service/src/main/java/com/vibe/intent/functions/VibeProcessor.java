package com.vibe.intent.functions;

import com.center.common.dto.MusicQuery;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.Part;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class VibeProcessor {
    private final Client client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public VibeProcessor(@Value("${app.api.google.key}") String key){
        this.client = Client.builder().apiKey(key).build();
    }
    public MusicQuery parseJson(String rawJson){
        try {
            return objectMapper.readValue(rawJson, MusicQuery.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse Gemini response: " + e.getMessage());
        }
    }
    public MusicQuery process(String prompt){
        String instruction = "you are a music expert, I need you go give me a response which consists of only Json"
                + "with these fields: searchQuery, tempo, mood, genre(s), based on my client request (or prompt): "
                + prompt + ". Also, dont ask any follow up question.";

        GenerateContentConfig config = GenerateContentConfig.builder()
                .responseMimeType("application/json")
                .systemInstruction(Content.fromParts(Part.fromText(instruction)))

                .build();
        try {
            GenerateContentResponse response = client.models.generateContent(
                    "gemini-3-flash-preview",
                    prompt,
                    config
            );

            System.out.println(response.text());
            return parseJson(response.text());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}