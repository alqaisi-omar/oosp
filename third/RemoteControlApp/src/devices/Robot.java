package devices;

import controllers.DeviceController;

public class Robot extends RemoteDevice {
    public Robot(DeviceController controller) {
        super(controller);
    }

    @Override
    public void operate() {
        controller.connect();
        controller.sendCommand("Robot starts moving.");
    }

    @Override
    public void shutdown() {
        controller.sendCommand("Robot stops.");
        controller.disconnect();
    }
}
