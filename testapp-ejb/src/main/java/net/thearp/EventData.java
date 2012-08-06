package net.thearp;

import java.io.Serializable;

/**
 * Created by
 * User: tahmid
 * Date: 8/5/12
 * Time: 1:30 PM
 */
public class EventData implements Serializable{

    private Long EventGenerationTime;
    private String EventId;

    public Long getEventGenerationTime() {
        return EventGenerationTime;
    }

    public void setEventGenerationTime(Long eventGenerationTime) {
        EventGenerationTime = eventGenerationTime;
    }

    public String getEventId() {
        return EventId;
    }

    public void setEventId(String eventId) {
        EventId = eventId;
    }
}
