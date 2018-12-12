package com.example.test.domain;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: Persons
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 13:54
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 13:54
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Persons {

    private int id;
    private String name;
    private int age;

    public Persons(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Persons{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
