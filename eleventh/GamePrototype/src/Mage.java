// Класс Mage, который наследует от класса Character и добавляет ману (энергию)
public class Mage extends Character {
    private final int mana; // Переменная для хранения маны персонажа

    // Конструктор, инициализирующий имя, здоровье, уровень и ману
    public Mage(String name, int health, int level, int mana) {
        super(name, health, level); // Вызов конструктора родительского класса Character
        this.mana = mana; // Инициализация маной
    }

    // Метод для получения значения маны
    public int getMana() {
        return mana;
    }

    // Переопределение метода clone() для клонирования объектов Mage
    @Override
    protected Mage clone() throws CloneNotSupportedException {
        return (Mage) super.clone(); // Вызов метода clone() родительского класса и приведение типа
    }

    // Переопределение метода displayInfo() для отображения информации о маге
    @Override
    public void displayInfo() {
        super.displayInfo(); // Вызов метода родительского класса для отображения базовой информации
        System.out.println("Mana: " + mana); // Вывод дополнительной информации о мане
    }
}