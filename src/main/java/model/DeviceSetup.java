package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeviceSetup {
    private static DeviceSetup instance;
    private boolean settingUp = false;
    private Device currentDevice;
    private List<Device> DEVICES = new ArrayList<Device>();

    private DeviceSetup() {
    }

    public static DeviceSetup getInstance() {
        if (instance == null) {
            instance = new DeviceSetup();
        }

        return instance;
    }

    public static void resetInstance() {
        instance = new DeviceSetup();
    }

    public boolean isSettingUp() {
        return settingUp;
    }

    public void startSettingUp() {
        settingUp = true;
    }

    public void endSettingUp() {
        settingUp = false;
    }

    public void newDevice() {
        currentDevice = new Device();
    }

    private void addDevice() {
        Optional<Device> identicalDevice = DEVICES.stream().filter(device -> device.equals(currentDevice)).findFirst();
        if (!identicalDevice.isPresent()) {
            DEVICES.add(currentDevice);
        }
    }

    public void updateDevice(Vote vote) {
        currentDevice.setDetails(vote);

        if (isCurrentDeviceFinished()) {
            addDevice();
        }
    }

    public boolean isCurrentDeviceFinished() {
        return currentDevice.isSetupComplete();
    }

    public List<Device> getDevices() {
        return DEVICES;
    }

    public void setDevices(List<Device> devices) {
        DEVICES = devices;
    }

    public Integer countOfDevices() {
        return DEVICES.size();
    }

    public boolean isCurrentDeviceSettingUp() {
        return currentDevice.isSettingUp();
    }
}
