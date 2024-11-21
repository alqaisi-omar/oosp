// Класс сотрудника, представляющий конечный элемент структуры (лист)
public class Employee implements OrganizationComponent {

    private final String name; // Имя сотрудника
    private final String position; // Должность сотрудника

    public Employee(String name, String position) {
        if (name == null || position == null) {
            throw new IllegalArgumentException("Имя и должность не могут быть null."); // Имя и должность не могут быть null
        }
        this.name = name;
        this.position = position;
    }

    @Override
    public String getName() {
        return name + ", " + position; // Вернуть имя и должность сотрудника
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + getName()); // Вывести имя и должность сотрудника с отступом
    }
}
