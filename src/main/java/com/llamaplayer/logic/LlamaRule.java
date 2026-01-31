package com.llamaplayer.logic;

public class LlamaRule {

    public enum Severity {
        PRACTICE_ONLY,
        LIGHT,
        MEDIUM,
        HEAVY,
        INSTANT_ANGY
    }

    private final String id;
    private final String description;
    private final Severity severity;

    public LlamaRule(String id, String description, Severity severity) {
        this.id = id;
        this.description = description;
        this.severity = severity;
    }

    public String id() {
        return id;
    }

    public String description() {
        return description;
    }

    public Severity severity() {
        return severity;
    }
}
