package model;

import builders.VoteBuilder;
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
        Vote vote = VoteBuilder.buildVote("BYRON_SX/0xA5");
        votes.add(vote);

        assertThat(votes.get(0), is(vote));
    }

    @Test
    void shouldKeepCountOfVotes() {
        Votes votes = new Votes();
        Vote vote = VoteBuilder.buildVote("BYRON_SX/0xA5");
        votes.add(vote);
        Vote voteTwo = VoteBuilder.buildVote("BYRON_SX/0xA5");
        votes.add(voteTwo);

        assertThat(votes.size(), is(2));
        assertThat(votes.get(1), is(voteTwo));
    }

}