package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class VoteDaoTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldAddVote() {
        Votes votes = new Votes();
        Vote vote = new Vote("topic", new Status("rssi"), 1);
        votes.add(vote);

        assertThat(votes.get(0), is(vote));
    }

    @Test
    void shouldKeepCountOfVotes() {
        Votes votes = new Votes();
        Vote vote = new Vote("topic", new Status("rssi"), 1);
        votes.add(vote);
        Vote voteTwo = new Vote("topicTwo", new Status("rssi"), 1);
        votes.add(voteTwo);

        assertThat(votes.size(), is(2));
        assertThat(votes.get(1), is(voteTwo));
    }

}