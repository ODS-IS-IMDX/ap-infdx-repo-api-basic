// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.exception.UserFaultException;

/**
 * URI未登録例外．<br>
 * ログ出力は行わない．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/12/26
 */
public class UriNotFoundException extends UserFaultException {

    private static final long serialVersionUID = 1L;
    
    public UriNotFoundException() {
        
        super();
        
    }

}
