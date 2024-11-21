// Класс Warrior, который наследует от класса Character и добавляет силу
public class Warrior extends Character {
    private final int strength; // Переменная для хранения силы персонажа (можно сделать final, так как сила не меняется после создания)

    // Конструктор, инициализирующий имя, здоровье, уровень и силу
    public Warrior(String name, int health, int level, int strength) {
        super(name, health, level); // Вызов конструктора родительского класса Character
        this.strength = strength; // Инициализация силы
    }

    // Метод для получения значения силы
    public int getStrength() {
        return strength;
    }

    // Переопределение метода clone() для клонирования объектов Warrior
    @Override
    protected Warrior clone() throws CloneNotSupportedException {
        return (Warrior) super.clone(); // Вызов метода clone() родительского класса и приведение типа
    }

    // Переопределение метода displayInfo() для отображения информации о воине
    @Override
    public void displayInfo() {
        super.displayInfo(); // Вызов метода родительского класса для отображения базовой информации
        System.out.println("Strength: " + strength); // Вывод дополнительной информации о силе
    }
}
