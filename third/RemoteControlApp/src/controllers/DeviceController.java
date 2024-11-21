package controllers;

public interface DeviceController {
    void connect();
    void sendCommand(String command);
    void disconnect();
}