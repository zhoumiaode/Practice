package com.example.test.test1;

/**
 * @ProjectName: test
 * @Package: com.example.test.test1
 * @ClassName: WindowsService
 * @Description: java类作用描述
 * @Author: zhoumiaode
 * @CreateDate: 2018/08/01 9:29
 * @UpdateUser: Neil.Zhou
 * @UpdateDate: 2018/08/01 9:29
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
public class WindowsService implements ListService {
    @Override
    public String showListCmd() {

        return "dir";
    }
}
