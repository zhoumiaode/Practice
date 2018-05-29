package com.example.test.controller;

import com.example.test.domain.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.Collator;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {





    @Autowired
    private MockMvc mvc;

    @Test
    public void girlList() throws Exception{
        MvcResult result=mvc.perform(MockMvcRequestBuilders.get("/girls").accept(MediaType.APPLICATION_JSON)).
                andDo(MockMvcResultHandlers.print()).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.status().is(200)).
                andReturn();

        

    }
    @Test
    public void testSort() {
        Person p1 = new Person(1l, "周杰伦");
        Person p2 = new Person(3l, "刘德华");
        Person p3 = new Person(2l, "张学友");
        Person p4 = new Person(4l, "成龙");
        Person p5 = new Person(5l, "胶布虫");

        List<Person> persons = Arrays.asList(p1,p2,p3,p4,p5);
       /* Collections.sort(persons, Comparator.comparing(Person::getName));*/
        Collections.sort(persons, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
                return com.compare(o1.getName(), o2.getName());

            }
        });
        for(Person pe:persons){
            System.out.println(pe.getName());
        }

    }
}