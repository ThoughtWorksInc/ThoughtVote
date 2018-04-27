package model;

public class Device {
    private Vote voteA;
    private Vote voteB;

    public void setDetails(Vote vote) {
        if (voteA == null) {
            voteA = vote;
        } else {
            voteB = vote;
        }
    }

    public boolean isSetupComplete() {
        return voteA != null && voteB != null;
    }

    public Vote getVoteA() {
        return voteA;
    }

    public Vote getVoteB() {
        return voteB;
    }

    public boolean isSettingUp() {
        return voteA != null && voteB == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (voteA != null ? !voteA.equals(device.voteA) : device.voteA != null) return false;
        return voteB != null ? voteB.equals(device.voteB) : device.voteB == null;
    }

    @Override
    public int hashCode() {
        int result = voteA != null ? voteA.hashCode() : 0;
        result = 31 * result + (voteB != null ? voteB.hashCode() : 0);
        return result;
    }
}
