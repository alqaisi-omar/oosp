
//Пример использования:

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("Ошибка при установке кодировки терминала: " + e.getMessage());
        }

        // Получение единственного экземпляра логгера
        EventLogger logger = EventLogger.getInstance();

        // Запись событий на английском языке
        logger.logEvent("Application started.");
        logger.logEvent("User logged in.");
        logger.logEvent("Error occurred while loading data.");

        // Закрытие логгера
        logger.close();
    }
}
