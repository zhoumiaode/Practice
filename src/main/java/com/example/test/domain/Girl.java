package com.example.test.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.text.Format;
import java.util.List;

@Entity
@Table(name = "girl")
public class Girl {
    @Id
    @GeneratedValue
    private Integer id;
    @NotNull
    private String name;
    private String cupSize;
    private Integer age;


    public Girl(){
        super();
    }
    public Girl(Integer id,String name,String cupSize,Integer age) {
        this.id=id;
        this.age=age;
        this.cupSize=cupSize;
        this.name=name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
