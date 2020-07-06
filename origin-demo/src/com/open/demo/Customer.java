package com.open.demo;

/**
 * @author chenkechao
 * @date 2020/7/5 2:05 下午
 */
public class Customer {

    public Customer() {
    }

    public Customer(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void test() {
        System.out.println("高考加油");
    }

    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
