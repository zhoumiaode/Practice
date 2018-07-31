package com.example.test.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: People
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:04
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:04
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Entity
@Table(name="people")
public class People {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonBackReference
    private Address address;


    public People(){

    super();
    }
    public People(String name, String sex) {
        this.name = name;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



}
