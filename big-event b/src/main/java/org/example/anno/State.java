package org.example.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.validation.StateValidation;
import java.lang.annotation.*;

@Target({ ElementType.FIELD}) //元注解
@Retention(RetentionPolicy.RUNTIME) //元注解，运行阶段
@Documented //元注解
@Constraint(
        validatedBy = {StateValidation.class} //指定提供校验规则的类
)
public @interface State {
    //提供校验失败后的信息
    String message() default "state参数的数值只能是已发布或者是草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载   获取到State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
