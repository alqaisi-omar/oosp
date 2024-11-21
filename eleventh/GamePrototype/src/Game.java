// Класс Game с методом main для демонстрации работы с клонированием объектов персонажей
public class Game {
    public static void main(String[] args) {
        try {
            // Создание объекта Mage с именем, здоровьем, уровнем и маной
            Mage mage = new Mage("Gandalf", 100, 10, 50);
            // Создание объекта Warrior с именем, здоровьем, уровнем и силой
            Warrior warrior = new Warrior("Aragorn", 120, 12, 70);

            // Клонирование объектов персонажей
            Mage clonedMage = mage.clone();
            Warrior clonedWarrior = warrior.clone();

            // Вывод информации о оригинальном маге
            System.out.println("Original Mage:");
            mage.displayInfo();
            // Вывод информации о клонированном маге
            System.out.println("Cloned Mage:");
            clonedMage.displayInfo();

            // Вывод информации о оригинальном воине
            System.out.println("\nOriginal Warrior:");
            warrior.displayInfo();
            // Вывод информации о клонированном воине
            System.out.println("Cloned Warrior:");
            clonedWarrior.displayInfo();
        } catch (CloneNotSupportedException e) {
            // Вывод сообщения об ошибке в случае, если клонирование не поддерживается
            System.err.println("An error occurred during cloning: " + e.getMessage());
        }
    }
}
