package controllers;

public class WiFiController implements DeviceController {
    @Override
    public void connect() {
        System.out.println("Connecting to device via Wi-Fi...");
    }

    @Override
    public void sendCommand(String command) {
        System.out.println("Sending command via Wi-Fi: " + command);
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from device via Wi-Fi.");
    }
}