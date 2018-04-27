package model;

import java.util.List;
import java.util.stream.Collectors;

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

    public boolean deviceAlreadyVoted(Vote voteToFindMatch) {
        List<Device> devicesContainingVote = devices.stream()
                .filter(device -> device.containsVote(voteToFindMatch))
                .collect(Collectors.toList());

        boolean alreadyMatchingVote = votes.stream()
                .anyMatch(anyVote -> devicesContainingVote.stream()
                        .anyMatch(device -> device.matchingVoteA(anyVote) || device.matchingVoteB(anyVote)));

        return alreadyMatchingVote;
    }
}
