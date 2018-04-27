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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        if (topic != null ? !topic.equals(vote.topic) : vote.topic != null) return false;
        return payload != null ? payload.equals(vote.payload) : vote.payload == null;
    }

    @Override
    public int hashCode() {
        int result = topic != null ? topic.hashCode() : 0;
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        return result;
    }
}
