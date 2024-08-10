package com.liuuu.admin.core.config;

import com.liuuu.common.framework.mybatis.plugin.interceptor.InsertUpdateMyBatisInterceptor;
import com.liuuu.framework.security.util.SecurityUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

/**
 * MyBatis插入更新拦截器配置
 *
 * @AUthor Liuuu
 * @Date 2024/8/8
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {
                MappedStatement.class, Object.class
        })
})
@Component
public class InsertUpdateMyBatisInterceptorConfig implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Long currentUserId = null;
        try {
            currentUserId = SecurityUtils.getUserId();
        } catch (Exception e) {

        }
        return InsertUpdateMyBatisInterceptor.intercept(invocation, currentUserId);
    }
}
