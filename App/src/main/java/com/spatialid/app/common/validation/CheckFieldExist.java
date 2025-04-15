// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckFieldExistValidator.class)
public @interface CheckFieldExist {
    String message() default "Required fields must not be empty.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields(); // バリデーション対象のフィールド名を指定
}