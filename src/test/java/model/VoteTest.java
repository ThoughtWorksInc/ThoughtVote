package model;

import builders.VoteBuilder;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class VoteTest {
    @Test
    public void shouldSerializeFromJSON() {
        Vote vote = VoteBuilder.buildVote("BYRON_SX/0xA5");

        assertThat(vote.getTopic(), is("BYRON_SX/0xA5"));
        assertThat(vote.getStatus().getRssi(), is("5"));
        assertThat(vote.getPayload(), is(1));
    }

}