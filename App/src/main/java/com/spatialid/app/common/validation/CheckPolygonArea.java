// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ポリゴン面積が許容値以内であることを検査するアノテーションクラス．<br>
 * nullを許容する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/25
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckPolygonAreaValidator.class)
public @interface CheckPolygonArea {
    
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
    int limit();
    
}
