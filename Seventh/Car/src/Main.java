public class Main {
    public static void main(String[] args) {
        //// Метод main для тестирования
        Car car = new Car.Builder()
                .setBrand("Tesla")
                .setModel("Model S")
                .setColor("Red")
                .setSunroof(true)
                .setGPS(true)
                .build();

        // Печать объекта Car
        System.out.println(car);
    }
}
