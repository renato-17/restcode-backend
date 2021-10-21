package upc.edu.pe.restcodebackend.resource.save;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class SaveAppointmentResource {
    @NotNull
    private Date currentDateTime;
    @NotNull
    private Date scheduleDateTime;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String topic;
    @NotNull
    @NotBlank
    @Size(max = 50)
    private String meetLink;


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

}
