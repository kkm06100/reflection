package org.example;

class Person {
    public String name;
    private int age;

    public static int height = 180;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public void getField() {
        System.out.printf("이름 : %s, 나이 : %d\n", name, age);
    }

    public int sum(int left, int right) {
        return left + right;
    }

    public static int staticSum(int left, int right) {
        return left + right;
    }

    private int privateSum(int left, int right) {
        return left + right;
    }
}