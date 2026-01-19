package com.example.resume_tailor;

import java.util.List;

public class ResumeRequest {
    private List<String> resume;
    private String jd;

    public List<String> getResume() {
        return resume;
    }

    public void setResume(List<String> resume) {
        this.resume = resume;
    }

    public String getJd() {
        return jd;
    }

    public void setJd(String jd) {
        this.jd = jd;
    }
}
