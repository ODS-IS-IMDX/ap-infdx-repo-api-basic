// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.InfoException;

/**
 * データ更新中例外クラス.<br>
 *
 * @author kawashima naoya
 * @version 1.1 2024/09/18
 */
public class DataUpdateFailureException extends InfoException {

    private static final long serialVersionUID = 1L;
        
    public DataUpdateFailureException() {
        
        super(LoggingConstant.KEY_INFO_MSG_DATA_UPDATE, null);
                
    }
    
}
