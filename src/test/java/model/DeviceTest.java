package model;

import builders.VoteBuilder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class DeviceTest {
    @Test
    void shouldKnowWhenNotYetFinished() {
        Vote vote = VoteBuilder.buildVote("BYRON_SX/0xA5");

        Device device = new Device();
        device.setDetails(vote);

        assertFalse(device.isSetupComplete());
    }

    @Test
    void shouldKnowWhenCompletedSetup() {
        Vote vote = VoteBuilder.buildVote("topicOne");
        Vote voteTwo = VoteBuilder.buildVote("topicTwo");

        Device device = new Device();

        device.setDetails(vote);
        device.setDetails(voteTwo);

        assertTrue(device.isSetupComplete());
    }

    @Test
    void shouldRetainBothVoters() {
        Vote voteA = VoteBuilder.buildVote("topicOne");
        Vote voteB = VoteBuilder.buildVote("topicTwo");

        Device device = new Device();

        device.setDetails(voteA);
        device.setDetails(voteB);

        assertThat(device.getVoteA(), is(voteA));
        assertThat(device.getVoteB(), is(voteB));
    }

    @Test
    void shouldKnowWhenDeviceUpdateHasHappened() {
        Vote voteA = VoteBuilder.buildVote("topicOne");
        Vote voteB = VoteBuilder.buildVote("topicTwo");

        Device device = new Device();

        assertFalse(device.isSettingUp());
        device.setDetails(voteA);
        assertTrue(device.isSettingUp());
        device.setDetails(voteB);
    }

}