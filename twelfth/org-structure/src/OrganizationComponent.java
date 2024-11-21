// Интерфейс для компонентов, используемых в организационной структуре
public interface OrganizationComponent {
    String getName();        // Получение имени компонента
    void show(String indent); // Отображение компонента с отступом
}
