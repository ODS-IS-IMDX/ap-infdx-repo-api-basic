// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * 日付形式チェックバリデータインターフェース
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String pattern();

    String message() default "Invalid date format";

    // nullを許可する設定
    boolean allowNull() default true;
    
    // 空文字を許可する設定
    boolean allowEmpty() default true;

}
