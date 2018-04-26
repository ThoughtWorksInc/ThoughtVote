package model;

public class Vote {
    private String topic;
    private Status status;
    private Integer payload;

    public Vote(String topic, Status status, Integer payload) {
        this.topic = topic;
        this.status = status;
        this.payload = payload;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPayload() {
        return payload;
    }

    public void setPayload(Integer payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "topic='" + topic + '\'' +
                ", status=" + status +
                ", payload=" + payload +
                '}';
    }
}
