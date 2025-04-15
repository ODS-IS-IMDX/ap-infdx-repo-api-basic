// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception;

import lombok.Getter;

/**
 * 即日対応が必要なエラーとして扱う例外を示すクラス．<br>
 * このクラスを継承した場合、ログにerrorとして出力される．<br>
 * 非検査例外
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
@Getter
public abstract class ErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /**
     * ログメッセージのキー名．
     */
    private final String msgKey;
    
    /**
     * 埋め込み文字．
     */
    private final String[] embeddingStrs;
    
    public ErrorException(String msgKey, String[] embeddingStrs) {
        
        super();
        
        this.msgKey = msgKey;
        
        this.embeddingStrs = embeddingStrs;
        
    }
    
}
