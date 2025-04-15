// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import com.spatialid.app.common.exception.UserFaultException;

import lombok.Getter;

/**
 * キー重複例外クラス.<br>
 * ログ出力は行わない．
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
@Getter
public class DuplicateKeyWrapException extends UserFaultException {

    private static final long serialVersionUID = 1L;
    
    /**
     * 埋め込み文字．
     */
    private final String duplicatedKey;

    public DuplicateKeyWrapException(String duplicatedKey) {

        super();
        
        this.duplicatedKey = duplicatedKey;
        
    }

}
