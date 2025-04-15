// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import java.util.Map;

import com.spatialid.app.common.exception.UserFaultException;

import lombok.Getter;

/**
 * パスパラメータで指定される対象リソースが存在しない例外クラス.<br>
 * ログ出力は行わない．
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
@Getter
public class NotFoundException extends UserFaultException {

    private static final long serialVersionUID = 1L;

    /**
     * DB上に存在しないパスパラメータとプログラム上での変数名を格納したマップ．
     */
    private final Map<String, String> invalidPathParams;

    public NotFoundException(Map<String, String> invalidPathParams) {

        super();
        
        this.invalidPathParams = invalidPathParams;
        
    }
    
}
