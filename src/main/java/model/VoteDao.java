package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VoteDao {

    private static final List<Vote> VOTES = new ArrayList<>();

    public static void add(Vote vote) {
        VOTES.add(vote);
    }
//
//    public static Vote find(String id) {
//        return VOTES.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
//    }
//
    public static List<Vote> all() {
        return VOTES;
    }

    public static Integer count() {
        return VOTES.size();
    }

    public static void clearVotes() {
        VOTES.clear();
    }
}
