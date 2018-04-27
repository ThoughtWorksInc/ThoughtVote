package model;

public class VoteDao {

    private static final Votes votes = new Votes();

    public static void add(Vote vote) {
        votes.add(vote);
    }

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
