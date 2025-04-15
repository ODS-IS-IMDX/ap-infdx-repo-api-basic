// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ポリゴン面積のバリデーションを実装するバリデーションクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/25
 */
public class CheckPolygonAreaValidator implements ConstraintValidator<CheckPolygonArea, Double> {
    
    /**
     * ポリゴン面積の許容上限．
     */
    private int limit;
    
    @Override
    public void initialize(CheckPolygonArea annotation) {
        
        this.limit = annotation.limit();
        
    }
    
    /**
     * ポリゴン面積が許容面積以内であることを検査する．
     * 
     * @param polygon ポリゴン面積．
     * @param context {@link ConstraintValidatorContext}
     * @return 最終的なバリデーション結果
     */
    @Override
    public boolean isValid(Double polygon, ConstraintValidatorContext context) {
        
        if (polygon == null) {
            
            return true;
            
        }
        
        return (polygon <= limit);
        
    }
    
}
