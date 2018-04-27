package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Devices extends ArrayList<Device> {

    public List<Integer> deviceIdsWithVote() {
        Votes votes = VoteDao.all();
        return this.stream()
                .filter(device -> votes.stream().anyMatch(device::containsVote))
                .map(device -> this.indexOf(device) + 1)
                .collect(Collectors.toList());
    }
}
