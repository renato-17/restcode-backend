package upc.edu.pe.restcodebackend.resource.save;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SaveConsultancyResource {
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String diagnosis;
    @NotNull
    @NotBlank
    @Size(max = 100)
    private String recommendation;

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
