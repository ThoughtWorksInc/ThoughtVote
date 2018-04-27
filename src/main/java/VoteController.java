import com.google.gson.Gson;
import model.Count;
import model.DeviceSetup;
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
        get("/", (req, res) -> renderVotes(req));

        get("/refreshVotes", (req, res) -> {
            Integer count = VoteDao.count();
            return new Gson()
                    .toJson(new Count(count));
        });

        post("/vote", (req, res) -> {
            String body = req.body();
            Vote vote = new Gson().fromJson(body, Vote.class);

            if (DeviceSetup.getInstance().isSettingUp()) {
                System.out.println("Setting up");
                System.out.println(vote);
                DeviceSetup.getInstance().updateDevice(vote);

                boolean currentDeviceFinished = DeviceSetup.getInstance().isCurrentDeviceFinished();
                if (currentDeviceFinished) {
                    System.out.println("Current device finished, moving to next device setup");

                    DeviceSetup.getInstance().newDevice();
                }

                return new Gson()
                        .toJson(new StandardResponse(currentDeviceFinished ? StatusResponse.STILL_SETTING_UP : StatusResponse.DONE_SETTING_UP));
            } else {

                res.type("application/json");

                System.out.println(vote);
                VoteDao.add(vote);
            }

            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        delete("/votes", (req, res) -> {
            System.out.println("Deleting all votes");
            VoteDao.clearVotes();
            return "";
        });

        // if the route didn't return anything
        after((req, res) -> {
            if (res.body() == null) {
                res.body(renderVotes(req));
            }
        });
    }

    private static String renderVotes(Request req) {
        Map<String, Object> votesModel = getVotesModel();
        if ("true".equals(req.queryParams("ic-request"))) {
            return renderTemplate("velocity/votes.vm", votesModel);
        }
        return renderTemplate("velocity/index.vm", votesModel);
    }

    private static Map<String, Object> getVotesModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("votes", VoteDao.all());
        model.put("voteCount", VoteDao.count());
        model.put("page", "votes");
        return model;
    }

    private static String renderTemplate(String template, Map model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }
}
