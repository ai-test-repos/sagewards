package com.sagewards.mcp.services;

import com.sagewards.mcp.data.PromptRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class GeminiLLMClient {

    @Value("${google.api.key}")
    private String apiKey;

    private final String endpoint = "https://generativelanguage.googleapis.com/v1/models/gemini-2.5-pro:generateContent";

    private final RestClient restClient = RestClient.create();

    public String callGemini(String prompt) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("x-goog-api-key", apiKey);

            PromptRequest payload = new PromptRequest(prompt);

            HttpEntity<PromptRequest> entity = new HttpEntity<>(payload, headers);

            ResponseEntity<Map> response = restClient.post()
                    .uri(endpoint)
                    .headers(httpHeaders -> {
                        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                        httpHeaders.set("x-goog-api-key", apiKey);
                    })
                    .body(payload)
                    .retrieve()
                    .toEntity(Map.class);

            return extractText(response.getBody());
        } catch (Exception e) {
            return "[Error calling Gemini API: " + e.getMessage() + "]";
        }
    }

    private String extractText(Map response) {
        try {
            List candidates = (List) response.get("candidates");
            if (candidates != null && !candidates.isEmpty()) {
                Map first = (Map) candidates.get(0);
                Map content = (Map) first.get("content");
                List parts = (List) content.get("parts");
                if (parts != null && !parts.isEmpty()) {
                    return (String) ((Map) parts.get(0)).get("text");
                }
            }
        } catch (Exception e) {
            return "[Failed to parse Gemini response]";
        }
        return "[No valid response from Gemini]";
    }
}
