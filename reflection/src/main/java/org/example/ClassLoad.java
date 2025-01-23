package org.example;

import org.example.CycleComponent2;

/**
 *
 * Class 객체 로딩 방법
 */
@CycleComponent2
public class ClassLoad implements CL{
    public void run(){
        System.out.println("run");
    }
//    public static void main(String[] args) {
//        // Object.getClass()
//        String str = new String("Class test");
//        Class<?> cls = str.getClass();
//        System.out.println(cls);
//
//        // class
//        Class<String> cls1 = String.class;
//        System.out.println(cls1);
//
//        try{
//            Class<?> cls2 = Class.forName("java.lang.String");
//            System.out.println(cls2);
//        }catch (ClassNotFoundException e){
//
//        }
//
//
//    }
}
