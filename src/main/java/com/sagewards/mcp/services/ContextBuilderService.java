package com.sagewards.mcp.services;

import com.sagewards.mcp.data.ContextSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContextBuilderService {
    @Autowired
    private MemoryStore memoryStore;

    @Autowired
    private ToolProvider toolProvider;

    public String buildContext(String userId, String intent) {
        String memory = memoryStore.getContextMemory(userId);
        List<String> tools = toolProvider.getSuggestedTools(intent);

        ContextSummary summary = new ContextSummary(userId, intent, memory, tools);
        return summary.formatAsText();
    }
}
