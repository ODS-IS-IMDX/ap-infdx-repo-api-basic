// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;

import java.math.BigInteger;
import java.util.List;

import org.springframework.util.CollectionUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;

/**
 * 空間IDの面積チェックを行うカスタムバリデータクラス.
 * 
 * @auther ukai jun
 * @version 1.1 2024.09.24
 * 
 */
@NoArgsConstructor
public class CheckSidAreaValidator implements ConstraintValidator<CheckSidArea, List<String>> {

    /**
     * ズームレベルの下限．
     */
    private int lowerLimit;
    
    /**
     * ズームレベルの上限．
     */
    private int upperLimit;
    
    @Override
    public void initialize(CheckSidArea annotation) {
        
        this.lowerLimit = annotation.lowerLimit();
        this.upperLimit = annotation.upperLimit();
        
    }

    /**
     * 空間ID面積チェックを行うメソッド
     * 
     * @param value   空間ID
     * @param context コンテキストデータ
     * 
     */
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        
        if (CollectionUtils.isEmpty(value)) {
            
            return true;
            
        }

        // 空間ID合計面積 xSum
        int xSum = 0;

        for (String element : value) {
            String[] elementSid = element.split("/");

            // ズームレベル値のチェック
            int zoomLevel = Integer.parseInt(elementSid[0]);

            // 空間ID面積チェック
            // xSumを計算
            BigInteger x = new BigInteger("4");
            xSum += x.pow(upperLimit - zoomLevel).intValue();
        }

        // yを計算
        BigInteger y = new BigInteger("4");

        // xSum > yの場合、エラー
        if (y.pow(upperLimit - lowerLimit).intValue() < xSum) {
            return false;
        }

        return true;
    }
}