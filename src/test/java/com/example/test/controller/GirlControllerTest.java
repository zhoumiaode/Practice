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
import java.util.Map.Entry;

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

    @Test
    public void testSort1() {
        Map<String,Double> persons =new HashMap<String,Double>();
        persons.put("122",3.1);
        persons.put("2112",2.1);
        persons.put("12",5.3);
        persons.put("112",1.4);
        persons.put("132",4.2);
        List<Map.Entry<String, Double>> cityInfoList = new ArrayList<Map.Entry<String, Double>>(persons.entrySet());
        Collections.sort(cityInfoList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int

            compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Map<String,Double> linkMap = new LinkedHashMap<>();
        for(Entry<String,Double> newEntry :cityInfoList){
            linkMap.put(newEntry.getKey(), newEntry.getValue());
        }
        for(Map.Entry<String, Double> mapEntry : linkMap.entrySet()){
            System.out.println("key:"+mapEntry.getKey()+"  value:"+mapEntry.getValue());
        }
    }
    @Test
    public void testSort2() {
       Map<String,Integer> map=new HashMap<>();
       map.put("12",12);
       map.put("123",123);
       map.put("1233",1);
       map.put("32",1233);
       List<Map.Entry<String,Integer>> list=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
       Collections.sort(list, new Comparator<Entry<String, Integer>>() {
           @Override
           public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
               return o2.getValue().compareTo(o1.getValue());
           }
       });
       Map<String,Integer> maps=new LinkedHashMap<>();
       for(Entry<String,Integer> en:list){
           maps.put(en.getKey(),en.getValue());
       }
       for(Map.Entry<String,Integer> mapEntry:maps.entrySet()){

           System.out.println("key:"+mapEntry.getKey()+"  value:"+mapEntry.getValue());
       }
    }

    @Test
    public void Test3(){

        Map<String,Integer> map=new HashMap<String, Integer>();
        map.put("12",4);
        map.put("44",5);
        map.put("3",1);
        map.put("123",2);
        List<Map.Entry<String,Integer>> l=new ArrayList<Entry<String, Integer>>(map.entrySet());
        Collections.sort(l, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        Map<String,Integer> maps=new HashMap<>();
        for(Entry<String,Integer> en:l){
            maps.put(en.getKey(),en.getValue());
        }

        for(Map.Entry<String,Integer> ee:maps.entrySet()){

            System.out.println(ee.getKey()+":"+ee.getValue());
        }

    }
}