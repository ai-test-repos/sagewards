package com.sagewards.mcp.controllers;

import com.sagewards.mcp.services.GeminiLLMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Map;

@RestController
public class TestController {
    private final RestClient restClient = RestClient.create();
    private final String apiKey = "AIzaSyC1Kzefn_rAG6RCt2PYRkgJpixXSc6eIpM";

    @Autowired
    private GeminiLLMClient geminiClient;

    @GetMapping("/t1")
    public String test1() {
        return "hello";
    }

    @GetMapping("/models")
    public Object listAvailableModels() {
        try {
            ResponseEntity<Map> response = restClient
                    .get()
                    .uri("https://generativelanguage.googleapis.com/v1beta/models")
                    .headers(headers -> headers.set("x-goog-api-key", apiKey))
                    .retrieve()
                    .toEntity(Map.class);

            return response.getBody();
        } catch (Exception e) {
            return Map.of(
                    "error", "Failed to fetch model list",
                    "message", e.getMessage()
            );
        }
    }

}
