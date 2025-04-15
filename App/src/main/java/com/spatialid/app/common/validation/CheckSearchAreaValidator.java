// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.util.List;

import org.springframework.util.CollectionUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * 取得対象領域のバリデーションを実装するバリデーションクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/23
 */
public class CheckSearchAreaValidator implements ConstraintValidator<CheckSearchArea, List<String>> {
    
    /**
     * 取得対象領域のリストに対して、座標数が3以上であるかを検査する．<br>
     * <p>
     * 緯度・経度のセットで1座標と数えるため、要素数では6以下となる．
     * </p>
     * 
     * @param searchArea 取得対象領域
     * @param context {@link ConstraintValidatorContext}
     * @return バリデーション結果
     */
    @Override
    public boolean isValid(List<String> searchArea, ConstraintValidatorContext context) {
        
        if (CollectionUtils.isEmpty(searchArea)) {
            
            return true;
            
        }
        
        return (6 <= searchArea.size());
        
    }
    
}
