package com.example.resume_tailor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend running locally to access
public class ResumeController {

    @PostMapping("/getSuggestions")
    public List<Suggestion> getSuggestions(@RequestBody ResumeRequest request) throws Exception {
        return SuggestionService.generateSuggestions(request.getResume(), request.getJd());
    }
}