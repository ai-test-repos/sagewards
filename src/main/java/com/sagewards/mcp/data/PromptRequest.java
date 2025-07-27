package com.sagewards.mcp.data;

import java.util.List;

public class PromptRequest {
    private List<Content> contents;

    public PromptRequest(String text) {
        this.contents = List.of(new Content(text));
    }

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }
}
