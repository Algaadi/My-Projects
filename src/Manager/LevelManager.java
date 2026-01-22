package Manager;

import Classes.Level;
import DataStructures.DoubleCircularLinkedList;

/**
 * LevelManager - يولّد مستويات افتراضية أو يجلب من DB (يمكن إضافة DAO لاحقاً)
 */
public class LevelManager {

    public DoubleCircularLinkedList<Level> getAllLevels() {
        DoubleCircularLinkedList<Level> list = new DoubleCircularLinkedList<>();
        list.add(new Level(1, "Level 1 - Easy", 60, 10, "Easy"));
        list.add(new Level(2, "Level 2 - Medium", 45, 15, "Medium"));
        list.add(new Level(3, "Level 3 - Hard", 30, 20, "Hard"));
        return list;
    }

    public Level getLevelById(int id) {
        for (Level l : getAllLevels()) {
            if (l.getLevel_id() == id) return l;
        }
        return null;
    }
}
