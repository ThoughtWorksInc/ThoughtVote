package model;

public enum StatusResponse {
    SUCCESS("Success"),
    ERROR("Error"),
    STILL_SETTING_UP("Still setting up..."),
    DONE_SETTING_UP("Completed setup!");

    private String status;

    StatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // constructors, getters
}
