// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception;

/**
 * ユーザー責任でありログに記録しないエラーとして扱う例外を示すクラス．<br>
 * 非検査例外
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
public abstract class UserFaultException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    public UserFaultException() {
        
        super();
        
    }
    
}
