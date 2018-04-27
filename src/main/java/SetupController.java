import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Device;
import model.DeviceSetup;
import model.StandardResponse;
import model.StatusResponse;
import spark.ModelAndView;
import spark.Request;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;

public class SetupController {
    public static void initialize() {

        get("/setup", (req, res) -> {
            System.out.println("Starting setup...");
            DeviceSetup.getInstance().startSettingUp();
            DeviceSetup.getInstance().newDevice();
            return renderSetUp(req, false);
        });

        post("/endSetup", (req, res) -> {
            System.out.println("Ending setup...");
            DeviceSetup.getInstance().endSettingUp();
            return "Success";
        });

        get("/setupRefresh", (req, res) -> renderSetUp(req, true));

        get("/devices", (req, res) -> new Gson()
                .toJson(DeviceSetup.getInstance().getDevices()));

        post("/devices", (req, res) -> {
            String body = req.body();
            ArrayList<Device> devicesList = new Gson().fromJson(body, new TypeToken<List<Device>>() {
            }.getType());
            DeviceSetup.getInstance().setDevices(devicesList);
            return new Gson()
                    .toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        delete("/device/:id", (req, res) -> {
            List<Device> devices = DeviceSetup.getInstance().getDevices();
            Integer id = Integer.parseInt(req.params("id"));
            System.out.println("attempting to delete...." + id);

            Optional<Device> devicesWithoutDeletedDevice = devices
                    .stream()
                    .filter(device -> device.getId().equals(id))
                    .findFirst();


            devicesWithoutDeletedDevice.ifPresent(device -> {
                System.out.println("Deleting device: " + device.getId() + " from id " + id);
                devices.remove(device);
                DeviceSetup.getInstance().setDevices(devices);
            });


            return "";
        });

    }

    private static String renderSetUp(Request req, boolean refresh) {
        Map<String, Object> setupModel = getSetupModel();
        if ("true".equals(req.queryParams("ic-request")) || refresh) {
            return renderTemplate("velocity/setUpDevices.vm", setupModel);
        }
        return renderTemplate("velocity/index.vm", setupModel);
    }

    private static Map<String, Object> getSetupModel() {
        Map<String, Object> model = new HashMap<>();
        model.put("devices", DeviceSetup.getInstance().getDevices());
        model.put("isSettingUpDevice", DeviceSetup.getInstance().isCurrentDeviceSettingUp());
        model.put("setupComplete", !DeviceSetup.getInstance().isSettingUp());
        model.put("deviceCount", DeviceSetup.getInstance().countOfDevices());
        model.put("page", "setup");
        return model;
    }

    private static String renderTemplate(String template, Map model) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, template));
    }
}
