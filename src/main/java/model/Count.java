package model;

import java.util.List;

public class Count {

    private final Votes votes;
    private final List<Device> devices;

    public Count(Votes votes, List<Device> devices) {
        this.votes = votes;
        this.devices = devices;
    }

    public Integer getVoteACount() {
        return Math.toIntExact(
                votes.stream().filter(vote -> devices.stream().anyMatch(
                        device -> device.matchingVoteA(vote))
                ).count()
        );
    }

    public Integer getVoteBCount() {
        return Math.toIntExact(
                votes.stream().filter(vote -> devices.stream().anyMatch(
                        device -> device.matchingVoteB(vote))
                ).count()
        );
    }
}
