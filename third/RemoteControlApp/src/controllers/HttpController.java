package controllers;

public class HttpController implements DeviceController {
    @Override
    public void connect() {
        System.out.println("Connecting to device via HTTP...");
    }

    @Override
    public void sendCommand(String command) {
        System.out.println("Sending command via HTTP: " + command);
    }

    @Override
    public void disconnect() {
        System.out.println("Disconnecting from device via HTTP.");
    }
}