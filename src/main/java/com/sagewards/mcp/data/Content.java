package com.sagewards.mcp.data;

import java.util.List;

public class Content {
    private List<Part> parts;

    public Content(String text) {
        this.parts = List.of(new Part(text));
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
