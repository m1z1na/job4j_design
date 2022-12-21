package ru.job4j.map;


import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserExec {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        /* Создать два объекта User, которые имеют одинаковые значения полей. */
        User user1 = new User("Вася", 0, calendar);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ ( hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("Вася", 0, calendar);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ ( hashCode2 >>> 16);
        int bucket2 = hash2 & 15;

        Map<User, Object> map = new HashMap<>(16);
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((key, value) -> System.out.println(key + ":" + value));
//        System.out.println(map.size());
//        System.out.println(user1 == user2);
//        System.out.println( "user1 :"  +  hashCode1 + "    "  + hash1 + "    " + bucket1);
//        System.out.println( "user2 :"  +  hashCode2 + "    "  + hash2 + "    " + bucket2);
    }

}


