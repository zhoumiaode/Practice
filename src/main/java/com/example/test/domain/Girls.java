package com.example.test.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: Girls
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/30 14:26
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/30 14:26
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class Girls implements Serializable{

    private int id;
    private String name;
    private int age;
    private List<Persons> persons;

    public List<Persons> getPersons() {
        return persons;
    }

    public void setPersons(List<Persons> persons) {
        this.persons = persons;
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

    public Girls(int id, String name, int age) {

        this.id = id;
        this.name = name;
        this.age = age;
    }
    public Girls() {
    }

    @Override
    public String toString() {
        return "Girls{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
