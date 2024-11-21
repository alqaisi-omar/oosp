import java.util.ArrayList;
import java.util.List;

// Класс отдела, который представляет узел в структуре и может содержать сотрудников или другие отделы
public class Department implements OrganizationComponent {

    private final String name; // Название отдела
    private final List<OrganizationComponent> components; // Список подкомпонентов (сотрудники или подотделы)

    public Department(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Название отдела не может быть null."); // Название отдела не может быть null
        }
        this.name = name;
        this.components = new ArrayList<>();
    }

    // Добавление компонента (сотрудник или отдел)
    public void add(OrganizationComponent component) {
        components.add(component); // Добавить подкомпонент в отдел
    }

    // Удаление компонента
    public void remove(OrganizationComponent component) {
        components.remove(component); // Удалить подкомпонент из отдела
    }

    @Override
    public String getName() {
        return name; // Вернуть название отдела
    }

    @Override
    public void show(String indent) {
        System.out.println(indent + getName() + " (Department)"); // Вывести название отдела с отступом
        for (OrganizationComponent component : components) {
            component.show(indent + "  "); // Рекурсивно вывести подкомпоненты
        }
    }
}
