// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception.subexception;

import java.util.Map;
import java.util.stream.Collectors;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.WarnException;

import lombok.Getter;

/**
 * アクセス履歴更新例外．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/11/29
 */
@Getter
public class AccessHistoryUpdateException extends WarnException {
    
    private static final long serialVersionUID = 1L;
    
    public AccessHistoryUpdateException(Map<String, Object> embeddingMap, Throwable cause) {
        
        super(LoggingConstant.KEY_WARN_MSG_FAILED_UPDATE_ACCESS_HISTORY,
                new String[] { embeddingMap.entrySet()
                    .stream()
                    .map(entry -> {
                        
                        final StringBuilder pairBuilder = new StringBuilder();
                        
                        return pairBuilder.append(entry.getKey())
                                .append(" = ")
                                .append(entry.getValue().toString())
                                .toString();
                        
                    })
                    .collect(Collectors.joining(","))}
                ,cause);
        
    }
        
}
