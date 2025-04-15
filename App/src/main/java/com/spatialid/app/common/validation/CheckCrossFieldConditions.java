// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 相関バリデーションのコンテナアノテーションを定義したクラス．<br>
 * バリデーション条件・内容を提供するアノテーションをネストして使用する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/22
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  CheckCrossFieldConditionsValidator.class)
public @interface CheckCrossFieldConditions {
    
    String message() default "";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    XorFields[] xorFields() default {};
    
    ConditionalMandatory[] conditionalMandatories() default {};
    
}
