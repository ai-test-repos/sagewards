package com.sagewards.mcp.controllers;

import com.sagewards.mcp.data.LLMQueryRequest;
import com.sagewards.mcp.services.ContextBuilderService;
import com.sagewards.mcp.services.GeminiLLMClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/mcp")
public class MCPController {

    @Autowired
    private ContextBuilderService contextBuilderService;

    @Autowired
    private GeminiLLMClient geminiClient;

    @PostMapping("/query")
    public ResponseEntity<Map<String, String>> handleQuery(@RequestBody LLMQueryRequest request) {
        String context = contextBuilderService.buildContext(request.getUserId(), request.getIntent());
        String response = geminiClient.callGemini(context);

        Map<String, String> result = new HashMap<>();
        result.put("response", response);
        return ResponseEntity.ok(result);
    }
}
