// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 付与されたフィールドが正の整数であることを検査するバリデーションクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/23
 */
public class CheckPositiveValidator implements ConstraintValidator<CheckPositive, Integer> {
    
    /**
     * 数値に対して正の整数であるかを検査する．
     * 
     * @param num コンテナアノテーションが付与されているフィールド．
     * @param context {@link ConstraintValidatorContext}
     * @return 最終的なバリデーション結果
     */
    @Override
    public boolean isValid(Integer num, ConstraintValidatorContext context) {
        
        if (num == null) {
            
            return true;
            
        }
        
        return 0 < num;
        
    }
    
}
