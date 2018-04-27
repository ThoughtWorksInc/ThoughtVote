package model;

import java.util.List;

public class VoteDao {

    private static final Votes votes = new Votes();

    public static void add(Vote vote) {
        votes.add(vote);
    }
//
//    public static Vote find(String id) {
//        return votes.stream().filter(t -> t.getId().equals(id)).findFirst().orElse(null);
//    }
//
    public static Votes all() {
        return votes;
    }

    public static Integer count() {
        return votes.size();
    }

    public static void clearVotes() {
        votes.clear();
    }
}
