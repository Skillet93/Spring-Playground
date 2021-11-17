package com.company.rs.reflection;

import java.lang.reflect.Field;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Student student = new Student();
        Field field = Student.class.getDeclaredField("indexNumber");
        field.setAccessible(true);
        String value = (String) field.get(student);
        System.out.println(value);
    }
}
