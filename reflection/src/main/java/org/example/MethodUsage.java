package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MethodUsage {
    public static void main(String[] args) throws Exception{
        Class<Person> personClass = Person.class;

        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);

        Person person = constructor.newInstance("kkm",18);
        person.getField();

        // method
        Method sum = personClass.getMethod("sum", int.class, int.class);
        System.out.println(sum.invoke(new Person(), 100, 100));

        // static method
        Method staticSum = personClass.getMethod("staticSum", int.class, int.class);
        System.out.println(staticSum.invoke(null, 100, 100));

        //private method
        Method privateSum = personClass.getDeclaredMethod("privateSum", int.class, int.class);
        privateSum.setAccessible(true); // allow to Access
        System.out.println(privateSum.invoke(new Person(), 100, 100));

    }
}
