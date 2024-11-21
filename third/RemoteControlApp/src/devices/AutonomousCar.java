package devices;

import controllers.DeviceController;

public class AutonomousCar extends RemoteDevice {
    public AutonomousCar(DeviceController controller) {
        super(controller);
    }

    @Override
    public void operate() {
        controller.connect();
        controller.sendCommand("Autonomous car starts driving.");
    }

    @Override
    public void shutdown() {
        controller.sendCommand("Autonomous car stops.");
        controller.disconnect();
    }
}