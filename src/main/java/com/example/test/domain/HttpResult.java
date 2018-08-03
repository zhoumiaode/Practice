package com.example.test.domain;

/**
 * @ProjectName: test
 * @Package: com.example.test.domain
 * @ClassName: HttpResult
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/03 14:37
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/03 14:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class HttpResult {

    /**
     * 状态码
     */
    private Integer status;
    /**
     * 返回数据
     */
    private String data;

    public HttpResult() {
    }

    public HttpResult(Integer status, String data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
