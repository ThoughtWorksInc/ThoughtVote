package model;

import builders.VoteBuilder;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class CountTest {
    Votes votes = new Votes();
    ArrayList<Device> devices = new ArrayList<>();

    @BeforeEach
    void setUp() {

        Vote voteA = VoteBuilder.buildVote("topicOne", 1);
        Vote voteB = VoteBuilder.buildVote("topicTwo", 3);

        Device deviceOne = new Device();

        deviceOne.setDetails(voteA);
        deviceOne.setDetails(voteB);

        Vote voteC = VoteBuilder.buildVote("topicThree", 2);
        Vote voteD = VoteBuilder.buildVote("topicFour", 8);

        Device deviceTwo = new Device();

        deviceTwo.setDetails(voteC);
        deviceTwo.setDetails(voteD);

        devices.add(deviceOne);
        devices.add(deviceTwo);

    }

    @Test
    void shouldKnowWhenSingleVoteForVoteAExists() {
        Vote vote = VoteBuilder.buildVote("topicOne", 1);
        votes.add(vote);

        Count count = new Count(votes, devices);

        assertThat(count.getVoteACount(), is(1));
        assertThat(count.getVoteBCount(), is(0));
    }

    @Test
    void shouldKnowWhenTwoVotesForVoteAExists() {
        Vote vote = VoteBuilder.buildVote("topicOne", 1);
        votes.add(vote);

        Vote voteTwo = VoteBuilder.buildVote("topicThree", 2);
        votes.add(voteTwo);

        Count count = new Count(votes, devices);

        assertThat(count.getVoteACount(), is(2));
        assertThat(count.getVoteBCount(), is(0));
    }

    @Test
    void shouldKnowWhenSingleVoteForVoteBExists() {
        Vote vote = VoteBuilder.buildVote("topicTwo", 3);
        votes.add(vote);

        Count count = new Count(votes, devices);

        assertThat(count.getVoteACount(), is(0));
        assertThat(count.getVoteBCount(), is(1));
    }

    @Test
    void shouldKnowWhenTwoVotesForVoteBExists() {
        Vote vote = VoteBuilder.buildVote("topicTwo", 3);
        votes.add(vote);

        Vote voteTwo = VoteBuilder.buildVote("topicFour", 8);
        votes.add(voteTwo);

        Count count = new Count(votes, devices);

        assertThat(count.getVoteACount(), is(0));
        assertThat(count.getVoteBCount(), is(2));
    }

    @Test
    void should() {
        Vote vote = VoteBuilder.buildVote("topicTwo", 3);
        votes.add(vote);

        Vote voteTwo = VoteBuilder.buildVote("topicFour", 8);
        votes.add(voteTwo);

        assertThat(new Gson()
                .toJson(new Count(votes, devices)), is(""));
    }

}