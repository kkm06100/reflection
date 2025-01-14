package org.example;

/**
 * Class 객체 로딩 방법
 */
public class ClassLoad {
    public static void main(String[] args) {
        // Object.getClass()
        String str = new String("Class test");
        Class<?> cls = str.getClass();
        System.out.println(cls);

        // class
        Class<String> cls1 = String.class;
        System.out.println(cls1);

        try{
            Class<?> cls2 = Class.forName("java.lang.String");
            System.out.println(cls2);
        }catch (ClassNotFoundException e){

        }


    }
}
