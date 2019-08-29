package code.Hash.Test;

import java.util.HashMap;
import java.util.HashSet;

public class HashCode {

    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer)a).hashCode());

        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415926;
        System.out.println(((Double)c).hashCode());

        String d = "banzhenghzheng";
        System.out.println(d.hashCode());

        System.out.println(new Student(2, 171, "ban", "zhengzheng").hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(new Student(2, 171, "ban", "zhengzheng"));

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(new Student(2, 171, "ban", "zhengzheng"), 100);
    }
}
