package com.example.test.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: Man
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/26 10:01
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/26 10:01
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Entity
public class Man {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private int age;

    public Man(){
        super();
    }
    public Man(int id, String name, int age) {
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
}
