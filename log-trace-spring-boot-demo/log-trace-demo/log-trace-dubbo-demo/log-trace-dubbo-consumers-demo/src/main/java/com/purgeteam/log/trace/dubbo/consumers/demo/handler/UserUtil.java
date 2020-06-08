package com.purgeteam.log.trace.dubbo.consumers.demo.handler;

import lombok.Builder;
import lombok.Data;

/**
 * 模拟用户工具类
 *
 * @author <a href="mailto:yaoonlyi@gmail.com">purgeyao</a>
 * @since 1.0.0
 */
public class UserUtil {

    /**
     * 模拟获取用户
     *
     * @return 用户
     */
    public static User getUserName() {
        return User.builder().code("16621370000").name("测试用户").build();
    }

    @Data
    @Builder
    public static class User {

        private String code;

        private String name;
    }
}
