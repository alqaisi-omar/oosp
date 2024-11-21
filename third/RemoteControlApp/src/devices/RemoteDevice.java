package devices;

import controllers.DeviceController;

public abstract class RemoteDevice {
    protected DeviceController controller;

    public RemoteDevice(DeviceController controller) {
        this.controller = controller;
    }

    public abstract void operate();
    public abstract void shutdown();
}