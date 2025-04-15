// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.spatialid.app.common.util.CommonUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link CheckAtLeastOneField}が付与されたクラスのフィールドに、最低でも1つは有効な値が存在することを検査するバリデーションクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/12/03
 */
public class CheckAtLeastOneFieldValidator implements ConstraintValidator<CheckAtLeastOneField, Object> {
        
    /**
     * クラスの全フィールドに対して、最低でも1つは有効な値が設定されているかを検査する．
     * 
     * @param annotatedClass コンテナアノテーションが付与されているクラス．
     * @param context {@link ConstraintValidatorContext}
     * @return 最終的なバリデーション結果
     */
    @Override
    public boolean isValid(Object annotatedClass, ConstraintValidatorContext context) {
        
        final BeanWrapper beanWrapper = new BeanWrapperImpl(annotatedClass);
        
        // 各プロパティの情報を取得
        final PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        
        // 無効な値を持つフィールドの名前と値をマップ形式で取得する
        final List<String> emptyFieldsList = Stream.of(propertyDescriptors)
                .filter(propertyDescriptor -> !("class".equals(propertyDescriptor.getName())))
                .map(propertyDescriptor -> {
                    
                    // プロパティ名を取得
                    final String propertyName = propertyDescriptor.getName();
                    
                    // 対象プロパティにGetterが存在することを確認
                    if (beanWrapper.isReadableProperty(propertyName)) {
                        
                        final Object propertyValue = beanWrapper.getPropertyValue(propertyName);
                        
                        if (CommonUtils.isEmpty(propertyValue)) {
                            
                            // プロパティ名を返却
                            return propertyName;
                            
                        }
                                                
                    }
                    
                    return null;

                })
                .filter(fieldName -> fieldName != null)
                .collect(Collectors.toList());
        
        context.disableDefaultConstraintViolation();
        
        // BindingResultから参照可能なバリデーション違反フィールドを設定する．
        emptyFieldsList.stream()
            .peek(fieldName -> context.buildConstraintViolationWithTemplate("AtLeastOneField Validation Error Occurred")
                    .addPropertyNode(fieldName)
                    .addConstraintViolation())
            .forEach(e -> {});
        
        // 無効な値を持つフィールド <= 総フィールド数[総プロパティ数 - クラス情報]がtrue = 少なくとも1つのフィールドは設定されている 
        return (emptyFieldsList.size() < (propertyDescriptors.length - 1));
        
    }
        
}
