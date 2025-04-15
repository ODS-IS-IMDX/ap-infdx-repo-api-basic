// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.resource;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * エラーレスポンス
 *
 * @author kawashima naoya
 */
@Data
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HTTPステータスフレーズ．
     */
    private String code;

    /**
     * エラー内容．
     */
    private String message;

    /**
     * エラー発生日時．
     */
    private String detail;
    
}
