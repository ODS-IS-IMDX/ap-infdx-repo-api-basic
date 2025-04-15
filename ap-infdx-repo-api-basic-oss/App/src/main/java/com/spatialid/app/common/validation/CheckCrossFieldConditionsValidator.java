// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.BeanWrapperImpl;
import com.spatialid.app.common.util.CommonUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 相関バリデーションを実装するバリデーションクラス．<br>
 * {@link CheckCrossFieldConditions}が付与されたクラスのフィールドを検査対象とする．
 * {@link CheckCrossFieldConditions}の引数に設定される相関バリデーションは、本クラスに実装される想定．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/23
 */
public class CheckCrossFieldConditionsValidator implements ConstraintValidator<CheckCrossFieldConditions, Object> {
    
    private XorFields[] xorFields;
    
    private ConditionalMandatory[] conditionalMandatories;
    
    @Override
    public void initialize(CheckCrossFieldConditions annotation) {
        
        this.xorFields = annotation.xorFields();
        this.conditionalMandatories = annotation.conditionalMandatories();
        
    }
    
    /**
     * コンテナアノテーション内に記述された相関バリデーションを行い、総体としてのバリデーション結果を返却する．
     * 
     * @param annotatedClass コンテナアノテーションが付与されているクラス．
     * @param context {@link ConstraintValidatorContext}
     * @return 最終的なバリデーション結果
     */
    @Override
    public boolean isValid(Object annotatedClass, ConstraintValidatorContext context) {
        
        final List<Boolean> validationResults = new ArrayList<Boolean>();
        
        context.disableDefaultConstraintViolation();
        
        validationResults.add(checkXorFields(annotatedClass, context));
        
        validationResults.add(checkConditionalMandatory(annotatedClass, context));
                
        return validationResults.stream()
                .allMatch(Boolean::booleanValue);
        
    }
    
    /**
     * 2つのフィールドのうち、特定のフィールドに値が設定されていた場合のみ一方のフィールドが必須であることを検証する．
     * <p>
     * {@link ConditionalMandatory}が付与されていない場合は、nullではなく空の配列が返却される．<br>
     * <br>
     * 以下のように、conditionalFieldに値が設定された場合のみ、mandatoryFieldを必須として扱う．<br>
     * private String conditionalField = null;<br>
     * private String mandatoryField = "value";
     * </p>
     * 
     * @param annotatedClass コンテナアノテーションが付与されているクラス．
     * @param context {@link ConstraintValidatorContext}
     * @return バリデーション違反となったアノテーションクラス
     */
    private boolean checkConditionalMandatory(Object annotatedClass, ConstraintValidatorContext context) {
        
        try {
            
            final ConditionalMandatory[] violatedAnnotations = Stream.of(conditionalMandatories)
                    .filter(conditionalMandatory -> {
                        
                        Object conditionalField = new BeanWrapperImpl(annotatedClass).getPropertyValue(conditionalMandatory.conditionalField());
                                                
                        if (CommonUtils.isEmpty(conditionalField)) {
                            
                            return false;
                            
                        }
                        
                        Object mandatoryField = new BeanWrapperImpl(annotatedClass).getPropertyValue(conditionalMandatory.mandatoryField());
                        
                        return CommonUtils.isEmpty(mandatoryField);
                        
                    })
                    .peek(violatedAnnotation -> setContext(context,
                            new String[] { violatedAnnotation.conditionalField(), violatedAnnotation.mandatoryField() }))
                    .toArray(ConditionalMandatory[]::new);
            
            return (violatedAnnotations.length == 0);
            
        } catch (Exception e) {
            
            throw e;
            
        }
        
    }
    
    /**
     * 2つのフィールドにおける値の設定が排他的に設定されていることを検証する．
     * <p>
     * {@link XorFields}が付与されていない場合は、nullではなく空の配列が返却される．<br>
     * <br>
     * 以下のように、指定された2フィールドのうち、どちらか片方がnullでない場合のみ正しいとみなす．<br>
     * private String firstField = null;<br>
     * private String secondField = "value";
     * </p>
     * 
     * @param annotatedClass コンテナアノテーションが付与されているクラス．
     * @param context {@link ConstraintValidatorContext}
     * @return バリデーション違反となったアノテーションクラス
     */
    private boolean checkXorFields(Object annotatedClass, ConstraintValidatorContext context) {
        
        try {
            
            final XorFields[] violatedAnnotations = Stream.of(xorFields)
                    .filter(xorField -> {
                        
                        Object firstField = new BeanWrapperImpl(annotatedClass).getPropertyValue(xorField.firstFieldName());
                        
                        Object secondField = new BeanWrapperImpl(annotatedClass).getPropertyValue(xorField.secondFieldName());
                        
                        return (CommonUtils.isEmpty(firstField)) == (CommonUtils.isEmpty(secondField));
                        
                    })
                    .peek(violatedAnnotation -> setContext(context,
                            new String[] { violatedAnnotation.firstFieldName(), violatedAnnotation.secondFieldName() }))
                    .toArray(XorFields[]::new);
            
            return (violatedAnnotations.length == 0);
            
        } catch (Exception e) {
            
            throw e;
            
        }

    }
    
    /**
     * {@link BindingResult}などで参照可能なバリデーション違反となったフィールドを設定する．
     * 
     * @param context {@link ConstraintValidatorContext}
     * @param violatedFieldName バリデーション違反となったフィールド名
     */
    private void setContext(ConstraintValidatorContext context, String[] violatedFieldName) {
        
        Stream.of(violatedFieldName)
            .peek(fieldName -> context.buildConstraintViolationWithTemplate("Cross Field Validation Error Occurred")
                    .addPropertyNode(fieldName)
                    .addConstraintViolation())
            .forEach(e -> {});
                
    }
    
}
