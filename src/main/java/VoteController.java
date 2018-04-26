import com.google.gson.Gson;
import model.Count;
import model.StandardResponse;
import model.StatusResponse;
import model.Vote;
import model.VoteDao;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

public class VoteController {
    public static void initialize() {
        get("/", (req, res) -> render(req));

        get("/refreshVotes", (req, res) -> {
            Integer count = VoteDao.count();
            return new Gson()
                    .toJson(new Count(count));
        });

//        {"status":{"rssi":5},"topic":"BYRON_SX/0xA5","payload":1,"_msgid":"8262acf4.7d9d5"}
        post("/vote", (req, res) -> {
            res.type("application/json");
            String body = req.body();
            Vote vote = new Gson().fromJson(body, Vote.class);

            System.out.println(vote);
            VoteDao.add(vote);
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        delete("/votes", (req, res) -> {
            VoteDao.clearVotes();
            return render(req);
        });

        // if the route didn't return anything
        after((req, res) -> {
            if (res.body() == null) {
                res.body(render(req));
            }
        });
    }

    private static String render(Request req) {
        if ("true".equals(req.queryParams("ic-request"))) {
            return renderTemplate("velocity/votes.vm", getVotesModel());
        }
        return renderTemplate("velocity/index.vm", getVotesModel());
    }

    private static Map<String, Object> getVotesModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("votes", VoteDao.all());
        model.put("voteCount", VoteDao.count());
        return model;
    }

    private static String renderTemplate(String template, Map model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }
}
