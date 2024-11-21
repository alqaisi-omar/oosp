// Класс Character, реализующий интерфейс Cloneable для клонирования объектов персонажей
public class Character implements Cloneable {
    private final String name; // Имя персонажа, задается при создании, не изменяется
    private final int health;  // Здоровье персонажа, задается при создании, не изменяется
    private final int level;   // Уровень персонажа, задается при создании, не изменяется

    // Конструктор для инициализации персонажа с заданными параметрами
    public Character(String name, int health, int level) {
        this.name = name;
        this.health = health;
        this.level = level;
    }

    // Метод для получения имени персонажа
    public String getName() {
        return name;
    }

    // Метод для получения здоровья персонажа
    public int getHealth() {
        return health;
    }

    // Метод для получения уровня персонажа
    public int getLevel() {
        return level;
    }

    // Метод для отображения информации о персонаже
    public void displayInfo() {
        System.out.println("Name: " + name + ", Health: " + health + ", Level: " + level);
    }

    // Переопределение метода clone() для клонирования объекта персонажа
    @Override
    protected Character clone() throws CloneNotSupportedException {
        // Вызов метода clone() суперкласса Object для создания копии объекта
        return (Character) super.clone();
    }
}
