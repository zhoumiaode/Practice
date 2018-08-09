package com.example.test.controller;

import com.example.test.domain.Girl;
import com.example.test.domain.Result;
import com.example.test.properties.GirlProperties;
import com.example.test.repository.GirlRepository;
import com.example.test.service.GirlService;
import com.example.test.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @Autowired
    private GirlProperties girlProperties;

    /**
     * 查询默认女生
     * @return
     */
    @GetMapping(value = "defaultGirl")
    public GirlProperties getGirlProperties(){

        return girlProperties;
    }
    /**
     * 查询女生列表
     * @return
     */
    @GetMapping(value="girls")
    public List<Girl> girlList(HttpServletRequest heq, ModelMap model) {
        HttpSession he=heq.getSession();
        System.out.println(he.getAttribute("name"));
        System.out.println("---"+model.get("name"));
        System.out.println(GirlController.class.getResource("/").getPath());
/*        System.out.println("!!!"+binder.getFieldMarkerPrefix());*/
        return girlRepository.findAll();
    }

    /**
     * 添加一个女生
     * @return
     */
    @PostMapping(value="girlss")
    public Object girlAdd(@Valid Girl girl, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Result result=new Result();
            result.setCode(1);
            result.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
        girl.setName(girl.getName());
        girl.setCupSize(girl.getCupSize());
        return ResultUtil.Success(girl);
    }

    @GetMapping(value="FindById/{id}")
    public Girl GirlFindOne(@PathVariable("id") Integer id){
        System.out.println(1);
        return girlService.findOne(id);
        //return girlRepository.findById(id).get();
    }

    @PutMapping(value="update")
    public Girl updateGirl(HttpServletRequest heq){
        HttpSession se=heq.getSession();
        se.setAttribute("name","jack");
        Girl girl=new Girl();
        girl.setId(3);
        girl.setCupSize("33");
        return girlRepository.save(girl);
    }

    @DeleteMapping(value="delete/{id}")
    public void deleteGirl(@PathVariable("id")Integer id){

        Girl girl=new Girl();
        girl.setId(id);
        girlRepository.delete(girl);
    }

    @GetMapping(value="girls/{name}")
    public List<Girl> findByName(@PathVariable("name")String name){


        return girlRepository.findByName(name);
    }

    @PostMapping(value="saveTwo")
    public void insertTwo(){

        girlService.insertTwo();
    }


    @GetMapping(value="girls/age/{id}")
    public void  getAge(@PathVariable(value = "id") Integer id)throws Exception{
        girlService.getAge(id);

    }

    @PostMapping(value="method1")
    public void method(){
        System.out.println(10);
        System.out.println(23);
    }

    @GetMapping(value="ab")
    public void findAD(@Param("name") String name,@Param("age") String age){

        Girl girl=girlService.findAB(name,Integer.parseInt(age));
        System.out.println(girl.getId());
    }

    @PostMapping(value = "ab")
    public int updateAB(@Param("name")String name,@Param("age")int age){

        return girlService.updateAB(name,age);
    }

    @GetMapping(value = "getAll")
    public Page<Girl> findAll(){
        Sort sort=new Sort(Sort.Direction.ASC,"age");
        Pageable pageable=new PageRequest(1,5,sort);
        return girlService.findAll(pageable);
    }

    /** 
    * @Description:  MyBatis数据库访问方法
    * @Param: []
    * @return: java.util.List<com.example.test.domain.Girl> 
    * @Author: zhoumiaode
    * @Date: 2018/07/30 
    */ 
    @GetMapping(value = "getAllMyBatis")
    public List<Girl> findAllByMyBatis(){
        List<Girl> girl=new ArrayList<Girl>();
        try {
            girl=girlService.findAllByMybatis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return girl;



    }

    @GetMapping(value = "findByIdMyBatis")
    public Girl findByIdMyBatis(@RequestParam(value = "id")Integer id){

        return girlService.findByIdMyBatis(id);
    }

    @GetMapping(value="GG")
    public List<Girl> girlList(HttpServletRequest request,@RequestParam("name") String name,@RequestParam("age")String age) {

        System.out.println(name);
        System.out.println(age);
        return girlRepository.findAll();
    }
}
