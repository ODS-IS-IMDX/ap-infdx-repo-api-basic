// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception;

import lombok.Getter;

/**
 * 翌営業日対応が可能なエラーとして扱う例外を示すクラス．<br>
 * このクラスを継承した場合、ログにwarnとして出力される．<br>
 * 非検査例外
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
@Getter
public abstract class WarnException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /**
     * ログメッセージのキー名．
     */
    private final String msgKey;
    
    /**
     * 埋め込み文字．
     */
    private final String[] embeddingStrs;
    
    public WarnException(String msgKey, String[] embeddingStrs, Throwable cause) {
        
        super(cause);
        
        this.msgKey = msgKey;
        
        this.embeddingStrs = embeddingStrs;

    }
    
    public WarnException(String msgKey, String[] embeddingStrs) {
        
        this(msgKey, embeddingStrs, null);
        
    }
        
}
