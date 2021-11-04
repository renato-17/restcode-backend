package upc.edu.pe.restcodebackend.resource;
import java.util.Date;

public class AppointmentResource {
    private Long id;
    private Date currentDateTime;
    private Date scheduleDateTime;
    private String topic;
    private String meetLink;
    private String ownerId;
    private String consultantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(Date currentDateTime) {
        this.currentDateTime = currentDateTime;
    }

    public Date getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(Date scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMeetLink() {
        return meetLink;
    }

    public void setMeetLink(String meetLink) {
        this.meetLink = meetLink;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }
}
