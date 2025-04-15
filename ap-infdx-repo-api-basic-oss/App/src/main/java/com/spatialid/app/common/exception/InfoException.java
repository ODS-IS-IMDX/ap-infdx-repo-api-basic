// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception;

import lombok.Getter;

/**
 * 対応の必要がないエラーとして扱う例外を示すクラス．<br>
 * このクラスを継承した場合、ログにinfoとして出力される．<br>
 * 非検査例外
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
@Getter
public abstract class InfoException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    /**
     * ログメッセージのキー名．
     */
    private final String msgKey;
    
    /**
     * 埋め込み文字．
     */
    private final String[] embeddingStrs;
    
    public InfoException(String msgKey, String[] embeddingStrs) {
        
        super();
        
        this.msgKey = msgKey;
        
        this.embeddingStrs = embeddingStrs;
        
    }

}
