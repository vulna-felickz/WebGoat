package org.owasp.webgoat.webwolf.requests;

public class MyDTO {
    private String eventId;

    public MyDTO(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return this.eventId;
    }
}