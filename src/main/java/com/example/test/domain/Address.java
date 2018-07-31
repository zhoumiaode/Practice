package com.example.test.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: Address
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/07/31 10:09
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/07/31 10:09
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue
    private  int id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "address")
    @JsonManagedReference
    private List<People> peoples;


    public Address() {
        super();
    }

    public Address(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<People> getPeoples() {
        return peoples;
    }

    public void setPeoples(List<People> peoples) {
        this.peoples = peoples;
    }

}
