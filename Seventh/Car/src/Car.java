public class Car {
    private final String brand; // Марка
    private final String model; // Модель
    private final String color; // Цвет
    private final boolean sunroof; // Люк на крыше
    private final boolean gps; // GPS

    // Приватный конструктор
    private Car(Builder builder) {
        this.brand = builder.brand;
        this.model = builder.model;
        this.color = builder.color;
        this.sunroof = builder.sunroof;
        this.gps = builder.gps;
    }

    // Геттеры для доступа к полям
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public boolean hasSunroof() {
        return sunroof;
    }

    public boolean hasGPS() { 
        return gps;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", sunroof=" + sunroof +
                ", gps=" + gps +
                '}';
    }

    // Статический вложенный класс Builder
    public static class Builder {
        private String brand;
        private String model;
        private String color;
        private boolean sunroof;
        private boolean gps;

        // Методы для настройки параметров
        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public Builder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public Builder setGPS(boolean gps) {
            this.gps = gps;
            return this;
        }

        // Метод для создания объекта Car
        public Car build() {
            return new Car(this);
        }
    }

}