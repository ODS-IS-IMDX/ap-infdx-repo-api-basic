// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.exception.UserFaultException;

import lombok.Getter;

/**
 * アクセス制御例外クラス.
 *
 * @author kawashima naoya
 * @version 1.1 2024/09/18
 */
@Getter
public final class AccessDeniedException extends UserFaultException {

    private static final long serialVersionUID = 1L;
    
    public AccessDeniedException() {
        
        super();
        
    }
    
}
