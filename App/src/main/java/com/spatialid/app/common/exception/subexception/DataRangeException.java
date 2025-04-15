// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.InfoException;

/**
 * データ整備範囲外の空間IDが指定された場合に送出される例外クラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/10
 */
public class DataRangeException extends InfoException {
    
    private static final long serialVersionUID = 1L;

    public DataRangeException() {
        
        super(LoggingConstant.KEY_INFO_MSG_DATA_RANGE, null);
        
    }
    
}
