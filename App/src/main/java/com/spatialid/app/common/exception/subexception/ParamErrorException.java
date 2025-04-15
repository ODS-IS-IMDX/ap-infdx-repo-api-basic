// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import java.util.Map;

import org.springframework.validation.BindingResult;

import com.spatialid.app.common.exception.UserFaultException;

import lombok.Getter;

/**
 * パラメータ例外クラス.<br>
 * ログ出力は行わない．
 * 
 * @author ukai jun
 * @version 1.1 2024/08/23
 */
@Getter
public class ParamErrorException extends UserFaultException {

    private static final long serialVersionUID = 1L;
    
    /**
     * バリデーション結果を保持したオブジェクト．
     */
    private final BindingResult br;
    
    /**
     * バリデーション結果を保持したオブジェクト．
     */
    private final Map<String, String> embeddingMap;

    private ParamErrorException(BindingResult br, Map<String, String> embeddingMap) {
        
        super();
        
        this.br = br;
        
        this.embeddingMap = embeddingMap;
        
    }
    
    public ParamErrorException(BindingResult br) {
        
        this(br, null);
        
    }
    
    public ParamErrorException(Map<String, String> map) {
        
        this(null, map);
        
    }
    
}