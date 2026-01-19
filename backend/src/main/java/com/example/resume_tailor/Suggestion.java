package com.example.resume_tailor;

public class Suggestion {
    private String original;
    private String suggested;
    private String reason;

    public Suggestion(String original, String suggested, String reason) {
        this.original = original;
        this.suggested = suggested;
        this.reason = reason;
    }

    public String getOriginal() {
        return original;
    }

    public String getSuggested() {
        return suggested;
    }

    public String getReason() {
        return reason;
    }
}