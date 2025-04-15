// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.ErrorException;

import lombok.Getter;

/**
 * 内部APIの呼び出しに失敗したことを示す例外クラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
@Getter
public class InternalApiCallingException extends ErrorException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 呼び出しに失敗したAPI名．
     */
    private final String apiName;
    
    public InternalApiCallingException(String apiName) {
        
        super(LoggingConstant.KEY_ERROR_MSG_API_CALLING, new String[] { apiName });
        
        this.apiName = apiName;
    }
    
}
