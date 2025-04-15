// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;

/**
 * 空間IDの許容ズームレベルチェックを行うカスタムバリデータクラス.
 * 
 * @auther ukai jun
 * @version 1.1 2024.09.24
 */
@NoArgsConstructor
public class CheckSidZoomLevelValidator implements ConstraintValidator<CheckSidZoomLevel, String> {

    /**
     * ズームレベルの下限．
     */
    private int lowerLimit;
    
    /**
     * ズームレベルの上限．
     */
    private int upperLimit;
    
    @Override
    public void initialize(CheckSidZoomLevel annotation) {
        
        this.lowerLimit = annotation.lowerLimit();
        this.upperLimit = annotation.upperLimit();
        
    }

    /**
     * ズームレベルの範囲チェックを行うメソッド.
     * 
     * @param value   空間ID
     * @param context コンテキストデータ
     * 
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if (value == null) {
            
            return true;
            
        }
        
        String[] elementSid = value.split("/");

        // ズームレベル値のチェック
        int zoomLevel = Integer.parseInt(elementSid[0]);
        if (zoomLevel < lowerLimit || upperLimit < zoomLevel) {

            // 空間IDのズームレベルが許容ズームレベル以上26以下ではない場合、エラー
            return false;
            
        }
        return true;
    }
}