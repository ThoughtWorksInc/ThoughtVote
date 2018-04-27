package builders;

import com.google.gson.Gson;
import model.Vote;

public class VoteBuilder {
    public static Vote buildVote(String topic) {
        return new Gson().fromJson("{\"status\":{\"rssi\":5},\"topic\":\"" + topic + "\",\"payload\":1,\"_msgid\":\"8262acf4.7d9d5\"}", Vote.class);
    }

    public static Vote buildVote(String topic, Integer payload) {
        return new Gson().fromJson("{\"status\":{\"rssi\":5},\"topic\":\"" + topic + "\",\"payload\":" + payload + ",\"_msgid\":\"8262acf4.7d9d5\"}", Vote.class);
    }
}
