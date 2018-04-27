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
}
