package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.anno.State;

// ConstraintValidator给哪个注解提供校验规则，校验的数据类型
public class StateValidation implements ConstraintValidator<State,String> {
    /*
    * value 将来要验证的数据
    * Context
    * @Return 如果返回false,则校验不通过，如果返回true，则校验通过。
    * */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext Context) {
        //提供校验规则
        if (value == null){
            return false;
        }
        if (value.equals("已发布") || value.equals("草稿")){
            return true;
        }
        return false;
    }
}
