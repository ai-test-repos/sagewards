package com.sagewards.mcp.services;

import org.springframework.stereotype.Component;

@Component
public class MemoryStore {
    public String getContextMemory(String userId) {
        return "Context memory for user " + userId + ". Prior task: summarized Q2 report.";
    }
}
