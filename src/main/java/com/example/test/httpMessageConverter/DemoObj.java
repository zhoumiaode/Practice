package com.example.test.httpMessageConverter;

/**
 * @ProjectName: test
 * @Package: com.example.test.httpMessageConverter
 * @ClassName: DemoObj
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/06 14:10
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/06 14:10
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class DemoObj {

    private Long id;
    private String name;

    public DemoObj(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoObj{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

