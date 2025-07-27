package com.sagewards.mcp.services;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToolProvider {
    public List<String> getSuggestedTools(String intent) {
        if (intent.toLowerCase().contains("summarize")) {
            return List.of("TextAnalyzer", "Highlighter");
        }
        return List.of("GenericTool");
    }
}
