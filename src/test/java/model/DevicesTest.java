package model;

import builders.VoteBuilder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class DevicesTest {
    @Test
    void shouldKnowOneDeviceHasBeenVotedOn() {
        Devices devices = new Devices();

        Device deviceOne = new Device(2);
        Device deviceTwo = new Device(3);

        Vote voteOne = VoteBuilder.buildVote("topic1");
        Vote voteTwo = VoteBuilder.buildVote("topic2");
        Vote voteThree = VoteBuilder.buildVote("topic3");
        VoteDao.add(voteOne);

        deviceOne.setDetails(voteOne);
        deviceOne.setDetails(voteTwo);

        deviceTwo.setDetails(voteTwo);
        deviceTwo.setDetails(voteThree);

        devices.add(deviceOne);
        devices.add(deviceTwo);

        ArrayList<Integer> expectedList = new ArrayList<>();
        expectedList.add(1);

        assertThat(devices.deviceIdsWithVote(), is(expectedList));
    }

    @Test
    void shouldKnowNoDevicesHaveBeenVotedOn() {
        Devices devices = new Devices();

        Device deviceOne = new Device(1);
        Device deviceTwo = new Device(2);

        Vote voteOne = VoteBuilder.buildVote("topic1");
        Vote voteTwo = VoteBuilder.buildVote("topic2");
        Vote voteThree = VoteBuilder.buildVote("topic3");
        Vote voteFour = VoteBuilder.buildVote("topic4");
        VoteDao.add(voteFour);

        deviceOne.setDetails(voteOne);
        deviceOne.setDetails(voteTwo);

        deviceTwo.setDetails(voteTwo);
        deviceTwo.setDetails(voteThree);

        devices.add(deviceOne);
        devices.add(deviceTwo);

        ArrayList<Integer> expectedList = new ArrayList<>();

        assertThat(devices.deviceIdsWithVote(), is(expectedList));
    }

}