// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.validation;



import com.spatialid.app.common.util.CommonUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CheckSidValidator implements ConstraintValidator<CheckSid, String> {
        
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
	    
	    if (value == null) {
	        
	        return true;
	        
	    }
	    
        String[] element_sid = value.split("/");
        if (!(element_sid.length == 4)) {
            return false;
        }

        // ズームレベル
        String stringz = element_sid[0];

        // 標高（鉛直方向）
        String stringf = element_sid[1];

        // 経度（東西方向）
        String stringx = element_sid[2];

        // 緯度（南北方向）
        String stringy = element_sid[3];

        // 標高（鉛直方向）の整数チェック
        if (CommonUtils.checkRegex(stringf) == false) {
            return false;
        }

        // ズームレベルの1以上の正の整数チェック
        if (CommonUtils.checkWholeNumber(stringz) == false) {
            return false;
        }

        // 経度（東西方向）の0以上の正の整数チェック
        if (CommonUtils.checkPositiveNumber(stringx) == false) {
            return false;
        }

        // 緯度（南北方向）の0以上の正の整数チェック
        if (CommonUtils.checkPositiveNumber(stringy) == false) {
            return false;
        }
	    
		return true;
		
	}
	
}
