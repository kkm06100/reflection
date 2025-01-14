package org.example;

import java.lang.reflect.Field;

public class FieldUsage {
    public static void main(String[] args) throws Exception{
        Class<Person> personClass = Person.class;

        // static field
        Field height = personClass.getField("height");
        height.set(null, 200);
        System.out.println(height.get(null));

        Person person = new Person("kkm", 18);
        Field name = personClass.getField("name");

        // private
        Field age = personClass.getDeclaredField("age");
        age.setAccessible(true);

        name.set(person, "kmk");
        age.set(person, 12);

        System.out.println(name.get(person));
        System.out.println(age.get(person));
    }
}
