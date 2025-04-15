// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TextNode;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 指定した複数のフィールドの中に、nullまたは空文字ではない値が存在することをチェックするカスタムバリデータクラス.
 * 
 * @Auther ukai jun
 * @Version 1.1 2024/09/13
 * 
 */
public class CheckFieldExistValidator implements ConstraintValidator<CheckFieldExist, Object> {

    /**
     * コンストラクタ.
     */
    public CheckFieldExistValidator() {
    }

    private String[] fields;

    /**
     * チェックするフィールドを設定するメソッド.
     * 
     * @Param constraintAnnotation
     */
    @Override
    public void initialize(CheckFieldExist constraintAnnotation) {
        this.fields = constraintAnnotation.fields();
    }

    /**
     * チェック処理を行うメソッド.
     * 
     * @Param obj
     * @Param context
     */
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj == null) {
            // nullオブジェクトは無効
            return false;
        }

        try {
            for (String fieldName : fields) {
                Field field = obj.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(obj);

                // JsonがTextNode("")の場合、Stringに変換
                if (value instanceof TextNode) {
                    TextNode textNode = (TextNode) value;
                    value = textNode.asText();
                }

                // JsonがNullNode(null)の場合、Nullを代入
                if (value instanceof NullNode) {
                    value = null;
                }

                // フィールドがnullでないかつ空文字ではない場合。チェックをクリア
                if (value != null && StringUtils.isNotEmpty(value.toString())) {

                    return true;

                } else {
                    // 全てnullか空文字の場合、エラー情報をバインド
                    addConstraintViolation(context, fieldName, value);
                }
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {

            return false;
        }
        return false;
    }

    /**
     * エラー情報をバインドするメソッド.
     * 
     * @param context
     * @param fieldName
     * @param rejectedValue
     */
    private void addConstraintViolation(ConstraintValidatorContext context, String fieldName, Object rejectedValue) {
        String message = String.format("Field '%s' with value '%s' is invalid.", fieldName, rejectedValue);
        // デフォルトのエラーメッセージを無効化
        context.disableDefaultConstraintViolation();
        // エラーメッセージを指定
        context.buildConstraintViolationWithTemplate(message)
                // フィールドに紐づけ
                .addPropertyNode(fieldName)
                // バリデーションエラーを追加
                .addConstraintViolation();
    }
}