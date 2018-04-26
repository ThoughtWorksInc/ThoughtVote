package model;

public class Status {
    private String rssi;

    public Status(String rssi) {
        this.rssi = rssi;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    @Override
    public String toString() {
        return "Status{" +
                "rssi='" + rssi + '\'' +
                '}';
    }
}
