package model;

import java.util.Comparator;
import java.util.Optional;

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

    public boolean matchingVoteA(Vote vote) {
        Votes votes = new Votes();
        votes.add(voteA);
        votes.add(voteB);

        Optional<Vote> minPayloadVote = votes.stream().min(Comparator.comparing(Vote::getPayload));
        if(minPayloadVote.isPresent()) {
            return minPayloadVote.get().equals(vote);
        } else {
            throw new RuntimeException("Could not compare votes!");
        }
    }

    public boolean matchingVoteB(Vote vote) {
        Votes votes = new Votes();
        votes.add(voteA);
        votes.add(voteB);

        Optional<Vote> maxPayloadVote = votes.stream().max(Comparator.comparing(Vote::getPayload));
        if(maxPayloadVote.isPresent()) {
            return maxPayloadVote.get().equals(vote);
        } else {
            throw new RuntimeException("Could not compare votes!");
        }
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

    @Override
    public String toString() {
        return "Device{" +
                "voteA=" + voteA +
                ", voteB=" + voteB +
                '}';
    }
}
