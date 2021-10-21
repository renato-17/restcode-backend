package upc.edu.pe.restcodebackend.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("consultant")
public class Consultant extends Profile{
    private String linkedinLink;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Assignment> assignments;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Publication> publications;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "consultant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;


    public String getLinkedinLink() {
        return linkedinLink;
    }

    public void setLinkedinLink(String linkedinLink) {
        this.linkedinLink = linkedinLink;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
