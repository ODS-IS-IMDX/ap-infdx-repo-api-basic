// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 付与されたクラスの持つフィールドに、最低でも1つの有効な値が存在していることを強制するアノテーションクラス．<br>
 * Getter経由で値を検査するため、Getterの存在しないフィールドは無視される．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/12/03
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckAtLeastOneFieldValidator.class)
public @interface CheckAtLeastOneField {
    
    String message() default "";
    
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
