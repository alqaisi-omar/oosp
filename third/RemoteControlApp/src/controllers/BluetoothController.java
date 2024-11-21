package controllers;

public class BluetoothController implements DeviceController {
    @Override
    public void connect() {
        System.out.println("Connecting to device via Bluetooth...");
    }

    @Override
    public void sendCommand(String command) {
        System.out.println("Sending command via Bluetooth: " + command);
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from device via Bluetooth.");
    }
}