package model;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class VoteTest {
    @Test
    public void shouldSerializeFromJSON() {
        Vote vote = new Gson().fromJson("{\"status\":{\"rssi\":5},\"topic\":\"BYRON_SX/0xA5\",\"payload\":1,\"_msgid\":\"8262acf4.7d9d5\"}", Vote.class);
        assertThat(vote.getTopic(), is("BYRON_SX/0xA5"));
        assertThat(vote.getStatus().getRssi(), is("5"));
        assertThat(vote.getPayload(), is(1));
    }

}