package upc.edu.pe.restcodebackend.resource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class ConsultancyResource {
    private Long id;
    private String diagnosis;
    private String recommendation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }
}
