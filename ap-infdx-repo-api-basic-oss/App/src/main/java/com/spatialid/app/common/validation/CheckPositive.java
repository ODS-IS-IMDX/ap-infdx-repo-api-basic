// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 付与された値が正の整数であることをチェックするアノテーション．<br>
 * フィールドと総称型の型宣言に対して付与される想定．<br>
 * Nullを許容．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/21
 */
@Target({ ElementType.FIELD, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { CheckPositiveValidator.class })
public @interface CheckPositive {
    
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
