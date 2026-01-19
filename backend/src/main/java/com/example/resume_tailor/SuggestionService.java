package com.example.resume_tailor;

import java.net.http.*;
import java.net.URI;
import java.net.http.HttpResponse.BodyHandlers;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

public class SuggestionService {

    private static final Set<String> KNOWN_TECH_SKILLS = new HashSet<>(Arrays.asList(
            "java", "spring boot", "mysql", "mongodb", "rest api", "graphql",
            "react", "node.js", "docker", "kubernetes", "aws", "azure", "git",
            "python", "flask", "django", "c++", "typescript", "pandas", "numpy",
            "tensorflow", "kotlin", "postman", "html", "css", "javascript"
    ));

    private static final String OPENAI_API_KEY = System.getenv("OPENAI_API_KEY"); // 🔒 Use env variable

    public static List<Suggestion> generateSuggestions(List<String> resumeLines, String jobDescription) throws Exception {
        List<Suggestion> suggestions = new ArrayList<>();
        Set<String> jdSkills = extractTechnicalKeywords(jobDescription);

        for (String line : resumeLines) {
            Set<String> missingSkills = new HashSet<>();

            for (String skill : jdSkills) {
                if (!line.toLowerCase().contains(skill.toLowerCase())) {
                    missingSkills.add(skill);
                }
            }

            if (missingSkills.isEmpty()) {
                suggestions.add(new Suggestion(line, line, "No change needed"));
            } else {
                String rewritten = rewriteLineWithOpenAI(line, missingSkills);
                String reason = "Improved to include: " + String.join(", ", missingSkills);
                suggestions.add(new Suggestion(line, rewritten, reason));
            }
        }

        return suggestions;
    }

    private static Set<String> extractTechnicalKeywords(String jd) {
        Set<String> extracted = new HashSet<>();
        String normalizedJD = jd.toLowerCase().replaceAll("[^a-z0-9 .]", " ");
        for (String skill : KNOWN_TECH_SKILLS) {
            if (normalizedJD.contains(skill.toLowerCase())) {
                extracted.add(skill.toLowerCase());
            }
        }
        return extracted;
    }

    private static String rewriteLineWithOpenAI(String line, Set<String> missingSkills) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String prompt = "Improve the following resume bullet by including the following technical skills naturally, even if it already sounds good , add all the extra missing points: "
                + String.join(", ", missingSkills) +
                ".\nMake it concise, professional, and action-oriented.\n\nOriginal: \"" + line + "\"";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o-mini");
        requestBody.put("messages", List.of(Map.of("role", "user", "content", prompt)));
        requestBody.put("temperature", 0.7);

        String requestBodyJson = mapper.writeValueAsString(requestBody);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + OPENAI_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBodyJson))
                .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        System.out.println("OpenAI Response Body: " + response.body()); // 🛠️ Debug print

        Map<String, Object> responseMap = mapper.readValue(response.body(), Map.class);
        List<Map<String, Object>> choices = (List<Map<String, Object>>) responseMap.get("choices");

        if (choices != null && !choices.isEmpty()) {
            Object messageObj = choices.get(0).get("message");
            if (messageObj instanceof Map) {
                Object content = ((Map<?, ?>) messageObj).get("content");
                if (content instanceof String) {
                    return ((String) content).trim().replaceAll("^\"|\"$", "").replace("\\/", "/");
                }
            }
        }

        return line + " (OpenAI rewrite failed)";
    }
}
