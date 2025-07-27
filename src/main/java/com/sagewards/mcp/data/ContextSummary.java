package com.sagewards.mcp.data;

import java.util.List;

public class ContextSummary {
    private final String userId;
    private final String intent;
    private final String memory;
    private final List<String> tools;

    public ContextSummary(String userId, String intent, String memory, List<String> tools) {
        this.userId = userId;
        this.intent = intent;
        this.memory = memory;
        this.tools = tools;
    }

    public String formatAsText() {
        return String.format("""
            Context Summary:
            ----------------
            • User ID   : %s
            • Intent    : %s
            • Memory    : %s
            • Tools     : %s

            Guidance:
            Please provide a clear and relevant response based on the above context.
            """, userId, intent, memory, String.join(", ", tools));
    }

    // Optionally add getters if needed
    public String getUserId() { return userId; }
    public String getIntent() { return intent; }
    public String getMemory() { return memory; }
    public List<String> getTools() { return tools; }
}

