package model;

import builders.VoteBuilder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VotesTest {
    @Test
    void shouldNotAddDuplicateVotes() {
        Vote vote = VoteBuilder.buildVote("topic1", 1);
        Vote duplicatVote = VoteBuilder.buildVote("topic1", 1);
        Votes votes = new Votes();

        votes.add(vote);
        votes.add(duplicatVote);

        assertThat(votes.size(), is(1));
    }

    @Test
    void shouldAddVote() {
        Votes votes = new Votes();
        Vote vote = VoteBuilder.buildVote("BYRON_SX/0xA5");
        votes.add(vote);

        assertThat(votes.size(), is(1));
        assertTrue(votes.contains(vote));
    }

}