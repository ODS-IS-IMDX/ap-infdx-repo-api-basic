// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;

import lombok.Getter;

/**
 * ExternalApi例外クラス.<br>
 *
 * @author kawashima naoya
 * @version 1.1 2024/09/18
 */

@Getter
public class ExternalApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final String ERRORMESSAGE_ERROR_MSGID = "error.api.internalservererror";

    private static final String LOG_ERROR_MSGID = "error.api.internalapierror";

    private String detailMessage;

    /**
     * 外部API例外の設定を行うメソッド.
     * 
     * @param MessageSource
     * @param apiName 対象API
     */
    public ExternalApiException(MessageSource messageSource, Exception ex) {
        super(ex.getMessage());
        this.detailMessage = getMessageDetailError(messageSource);
    }

    /**
     * 外部API例外の設定を行うメソッド.
     * 
     * @param MessageSource
     * @param apiName 対象API
     */
    public ExternalApiException(MessageSource messageSource, Object[] apiName) {
        super(messageSource.getMessage(LOG_ERROR_MSGID, apiName, Locale.JAPAN));
        this.detailMessage = getMessageDetailError(messageSource);
    }

    /**
     * リクエストパラメータメッセージを編集する.<br>
     *
     * @return パラメータエラーメッセージ
     */
    private String getMessageDetailError(MessageSource messageSource) {
        return messageSource.getMessage(ERRORMESSAGE_ERROR_MSGID, null, Locale.JAPAN);
    }

}
