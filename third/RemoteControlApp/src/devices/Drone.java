package devices;

import controllers.DeviceController;

public class Drone extends RemoteDevice {
    public Drone(DeviceController controller) {
        super(controller);
    }

    @Override
    public void operate() {
        controller.connect();
        controller.sendCommand("Drone takes off.");
    }

    @Override
    public void shutdown() {
        controller.sendCommand("Drone lands.");
        controller.disconnect();
    }
}
