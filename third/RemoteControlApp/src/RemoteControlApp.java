import controllers.BluetoothController;
import controllers.DeviceController;
import controllers.HttpController;
import controllers.WiFiController;
import devices.AutonomousCar;
import devices.Drone;
import devices.RemoteDevice;
import devices.Robot;
import java.util.Scanner;

public class RemoteControlApp {
    public static void run() {
        try (Scanner scanner = new Scanner(System.in)) { 
            System.out.println("Select a device type:");
            System.out.println("1 - Robot");
            System.out.println("2 - Drone");
            System.out.println("3 - Autonomous Car");
            int deviceChoice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            System.out.println("Select a connection method:");
            System.out.println("1 - Wi-Fi");
            System.out.println("2 - Bluetooth");
            System.out.println("3 - HTTP");
            int connectionChoice = scanner.nextInt();
            scanner.nextLine();  
            DeviceController controller = switch (connectionChoice) {
                case 1 -> new WiFiController();
                case 2 -> new BluetoothController();
                case 3 -> new HttpController();
                default -> throw new IllegalArgumentException("Invalid connection type.");
            };

            RemoteDevice device = switch (deviceChoice) {
                case 1 -> new Robot(controller);
                case 2 -> new Drone(controller);
                case 3 -> new AutonomousCar(controller);
                default -> throw new IllegalArgumentException("Invalid device type.");
            };

            System.out.println("Starting device operation...");
            device.operate();

            System.out.println("Press Enter to shut down the device...");
            scanner.nextLine();

            device.shutdown();
            System.out.println("Device operation ended.");
        } 
    }
}
