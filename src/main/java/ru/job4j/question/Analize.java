package ru.job4j.question;

import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        Map<Integer, User> currentMap = new HashMap<>();
        for (User user : current) {
            currentMap.put(user.getId(), user);
        }
        for (User user : previous) {
            if (!currentMap.containsKey(user.getId())) {
                deleted++;
            }
            if (currentMap.containsKey(user.getId()) && !user.equals(currentMap.get(user.getId()))) {
                changed++;
            }
        }
        added = current.size() - previous.size() + deleted;
    return new Info(added, changed, deleted);
    }
}
