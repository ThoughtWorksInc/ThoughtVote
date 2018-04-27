package model;

import builders.VoteBuilder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldKnowWhenNotMatchingAnyVote() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        Vote notMatchingVote = VoteBuilder.buildVote("notMatchingTopic", 1);

        assertFalse(device.matchingVoteA(notMatchingVote));
    }

    @Test
    void shouldKnowWhenNotMatchingVoteA() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        Vote notMatchingVote = VoteBuilder.buildVote("topicTwo", 3);

        assertFalse(device.matchingVoteA(notMatchingVote));
    }

    @Test
    void shouldKnowWhenMatchingVoteA() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        assertTrue(device.matchingVoteA(VoteBuilder.buildVote("topicOne", 1)));
    }

    @Test
    void shouldKnowWhenNotMatchingVoteB() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        Vote notMatchingVote = VoteBuilder.buildVote("topicOne", 1);

        assertFalse(device.matchingVoteB(notMatchingVote));
    }

    @Test
    void shouldKnowWhenMatchingVoteB() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        assertTrue(device.matchingVoteB(VoteBuilder.buildVote("topicTwo", 3)));
    }

    @Test
    void shouldKnowWhenContainingMultipleIdenticalVotes() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicOne", 1);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        assertFalse(device.doesNotContainIdenticalVotes());
    }

    @Test
    void shouldKnowWhenNotContainingMultipleIdenticalVotes() {
        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 1);

        Device device = new Device();
        device.setDetails(voteA);
        device.setDetails(voteB);

        assertTrue(device.doesNotContainIdenticalVotes());
    }

}