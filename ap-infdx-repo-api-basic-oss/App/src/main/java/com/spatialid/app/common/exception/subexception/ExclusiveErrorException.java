// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.InfoException;

/**
 * 排他制御ですでにデータ更新がされていた場合に送出される例外クラス．
 * 
 * @author ukai jun
 * @version 1.1 2024/12/02
 */
public class ExclusiveErrorException extends InfoException {
    
    private static final long serialVersionUID = 1L;

    public ExclusiveErrorException() {
        
        super(LoggingConstant.KEY_INFO_MSG_EXCLUSIVE, null);
        
    }
    
}
