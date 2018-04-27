package model;

import builders.VoteBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

class DeviceSetupTest {

    @Test
    void shouldKnowIfSettingUpOrNot() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        assertFalse(deviceSetup.isSettingUp());
        deviceSetup.startSettingUp();
        assertTrue(deviceSetup.isSettingUp());
    }

    @Test
    void shouldCompleteSettingUp() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        assertTrue(deviceSetup.isSettingUp());
        deviceSetup.endSettingUp();
        assertFalse(deviceSetup.isSettingUp());
    }

    @Test
    void shouldKnowWhenDeviceIsNotComplete() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        deviceSetup.newDevice();
        deviceSetup.updateDevice(VoteBuilder.buildVote("test"));
        assertFalse(deviceSetup.isCurrentDeviceFinished());
    }

    @Test
    void shouldKnowWhenDeviceIsComplete() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        deviceSetup.newDevice();
        deviceSetup.updateDevice(VoteBuilder.buildVote("test"));
        deviceSetup.updateDevice(VoteBuilder.buildVote("test2"));
        assertTrue(deviceSetup.isCurrentDeviceFinished());
    }

    @Test
    void shouldAddDeviceWhenComplete() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        deviceSetup.newDevice();
        Vote voteA = VoteBuilder.buildVote("test");
        deviceSetup.updateDevice(voteA);
        Vote voteB = VoteBuilder.buildVote("test2");
        deviceSetup.updateDevice(voteB);
        assertTrue(deviceSetup.isCurrentDeviceFinished());
        assertThat(deviceSetup.getDevices().get(0).getVoteA(), is(voteA));
        assertThat(deviceSetup.getDevices().get(0).getVoteB(), is(voteB));
        assertThat(deviceSetup.countOfDevices(), is(1));
    }

    @Test
    void shouldAddTwoDevices() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();

        deviceSetup.newDevice();
        Vote voteA = VoteBuilder.buildVote("test");
        deviceSetup.updateDevice(voteA);
        Vote voteB = VoteBuilder.buildVote("test2");
        deviceSetup.updateDevice(voteB);

        deviceSetup.newDevice();
        voteA = VoteBuilder.buildVote("test3");
        deviceSetup.updateDevice(voteA);
        voteB = VoteBuilder.buildVote("test4");
        deviceSetup.updateDevice(voteB);

        assertThat(deviceSetup.countOfDevices(), is(2));
        assertThat(deviceSetup.getDevices().get(0).getVoteA().getTopic(), is("test"));
        assertThat(deviceSetup.getDevices().get(0).getVoteB().getTopic(), is("test2"));

        assertThat(deviceSetup.getDevices().get(1).getVoteA().getTopic(), is("test3"));
        assertThat(deviceSetup.getDevices().get(1).getVoteB().getTopic(), is("test4"));
    }

    @Test
    void shouldSerializeToJSON() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        deviceSetup.newDevice();
        Vote voteA = VoteBuilder.buildVote("test");
        deviceSetup.updateDevice(voteA);
        Vote voteB = VoteBuilder.buildVote("test2");
        deviceSetup.updateDevice(voteB);
        assertThat(new Gson()
                .toJson(DeviceSetup.getInstance().getDevices()), is("[{\"voteA\":{\"topic\":\"test\",\"status\":{\"rssi\":\"5\"},\"payload\":1},\"voteB\":{\"topic\":\"test2\",\"status\":{\"rssi\":\"5\"},\"payload\":1}}]"));
    }

    @Test
    void shouldDeserializeFromJSON() {
        DeviceSetup deviceSetup = DeviceSetup.getInstance();
        deviceSetup.startSettingUp();
        deviceSetup.newDevice();
        Vote voteA = VoteBuilder.buildVote("test");
        deviceSetup.updateDevice(voteA);
        Vote voteB = VoteBuilder.buildVote("test2");
        deviceSetup.updateDevice(voteB);
        String json = "[{\"voteA\":{\"topic\":\"test\",\"status\":{\"rssi\":\"5\"},\"payload\":1},\"voteB\":{\"topic\":\"test2\",\"status\":{\"rssi\":\"5\"},\"payload\":1}}]";
        ArrayList<Device> devicesList = new Gson().fromJson(json, new TypeToken<List<Device>>(){}.getType());
        assertThat(devicesList.size(), is(1));
        assertThat(devicesList.get(0).getVoteA().getTopic(), is("test"));
        assertThat(devicesList.get(0).getVoteB().getTopic(), is("test2"));
    }

    @AfterEach
    void tearDown() {
        DeviceSetup.resetInstance();
    }
}