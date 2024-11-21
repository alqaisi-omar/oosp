import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventLogger {

    // Единственный экземпляр (Singleton)
    private static EventLogger instance;

    // Имя файла журнала
    private static final String LOG_FILE = "events.log";

    // Поток для записи в файл
    private BufferedWriter writer;

    // Приватный конструктор для реализации Singleton
    private EventLogger() {
        try {
            // Инициализация потока записи с кодировкой UTF-8
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(LOG_FILE, true), StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Ошибка при открытии файла журнала: " + e.getMessage());
        }
    }

    // Метод для получения единственного экземпляра класса
    public static synchronized EventLogger getInstance() {
        if (instance == null) {
            instance = new EventLogger();
        }
        return instance;
    }

    // Метод для записи события в журнал
    public synchronized void logEvent(String event) {
        // Получение текущего времени
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        // Формирование строки для записи в журнал
        String logMessage = "[" + timestamp + "] " + event;

        try {
            if (writer != null) {
                writer.write(logMessage); // Запись события в файл
                writer.newLine();
                writer.flush(); // Принудительное сохранение данных
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи события: " + e.getMessage());
        }

        // Вывод события в терминал
        System.out.println(logMessage);
    }

    // Метод для закрытия ресурсов
    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии файла журнала: " + e.getMessage());
        }
    }
}
