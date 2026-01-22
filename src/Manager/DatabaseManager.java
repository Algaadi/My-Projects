package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseManager {

    // اسم قاعدة البيانات حسب اسم المشروع/الملف
    private static final String DB_NAME = "FastestGame";

    // تقدر تغيّر الإعدادات من هنا أو عبر متغيرات البيئة:
    // FASTESTGAME_DB_URL / FASTESTGAME_DB_USER / FASTESTGAME_DB_PASSWORD
    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/" + DB_NAME +
            "?useUnicode=true&characterEncoding=UTF-8" +
            "&useSSL=false&allowPublicKeyRetrieval=true" +
            "&serverTimezone=UTC";

    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = ""; // غيّره لو عندك كلمة مرور

    private static final String URL = envOrDefault("FASTESTGAME_DB_URL", DEFAULT_URL);
    private static final String USER = envOrDefault("FASTESTGAME_DB_USER", DEFAULT_USER);
    private static final String PASSWORD = envOrDefault("FASTESTGAME_DB_PASSWORD", DEFAULT_PASSWORD);

    static {
        // تحميل Driver بشكل صريح (اختياري، لكن يساعد إذا IDE ما حمله تلقائياً)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
            // إذا ما تم العثور على الدرايفر: غالباً ما أضفت mysql-connector-j للمشروع
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static String envOrDefault(String key, String def) {
        String v = System.getenv(key);
        return (v == null || v.isBlank()) ? def : v;
    }
}
