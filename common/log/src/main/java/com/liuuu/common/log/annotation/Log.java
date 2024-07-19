package com.liuuu.common.log.annotation;

import com.liuuu.common.log.enums.BusinessType;
import com.liuuu.common.log.enums.OperateType;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块名称
     * @return
     */
    String moduleName() default "";

    /**
     * 业务类型
     * @return
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作类型
     * @return
     */
    OperateType operateType() default OperateType.ADMIN;

    /**
     * 是否保存请求数据
     * @return
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应数据
     * @return
     */
    boolean isSaveResponseData() default true;
}
