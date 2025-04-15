// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 日付形式チェックバリデータ.
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

    // 日付フォーマット
    private String format;
    // Null許容設定
    private boolean allowNull;
    // 空文字許容設定
    private boolean allowEmpty;

    /**
     * コンストラクタ.
     */
    public ValidDateValidator() {
    }

    /**
     * アノテーションから日付フォーマットを取得し、設定するメソッド.
     * 
     * @param formatDate アノテーションのインスタンス.
     */
    @Override
    public void initialize(ValidDate annotation) {
        this.format = annotation.pattern();
        this.allowNull = annotation.allowNull();
        this.allowEmpty = annotation.allowEmpty();
    }

    /**
     * 指定した日付フォーマットのバリデーション処理をするメソッド.
     * 
     * @param value   指定したフィールドの値.
     * @param context コンテキスト.
     * @return boolean
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            
            return allowNull;
            
        } else if(StringUtils.isEmpty(value)) {
            
            return allowEmpty;
            
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format) 
                    .withResolverStyle(ResolverStyle.STRICT)
                    ;
            LocalDate.parse(value, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
