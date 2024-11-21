// Главный класс для тестирования программы
public class Main {
    public static void main(String[] args) {
        // Создание сотрудников
        Employee alice = new Employee("Alice", "Developer"); // Создать сотрудника по имени Alice
        Employee bob = new Employee("Bob", "Designer"); // Создать сотрудника по имени Bob
        Employee charlie = new Employee("Charlie", "Manager"); // Создать сотрудника по имени Charlie

        // Создание отделов
        Department itDepartment = new Department("IT Department"); // Создать отдел IT
        Department designDepartment = new Department("Design Department"); // Создать отдел дизайна

        // Добавление сотрудников в отделы
        itDepartment.add(alice); // Добавить Alice в отдел IT
        designDepartment.add(bob); // Добавить Bob в отдел дизайна

        // Создание отдела HR и добавление сотрудника
        Department hrDepartment = new Department("HR Department"); // Создать отдел HR
        hrDepartment.add(charlie); // Добавить Charlie в отдел HR

        // Создание главного отдела, содержащего все другие отделы
        Department company = new Department("Company"); // Создать главный отдел компании
        company.add(itDepartment); // Добавить отдел IT в компанию
        company.add(designDepartment); // Добавить отдел дизайна в компанию
        company.add(hrDepartment); // Добавить отдел HR в компанию

        // Отображение всей организационной структуры
        company.show(""); // Вывести всю организационную структуру
    }
}
