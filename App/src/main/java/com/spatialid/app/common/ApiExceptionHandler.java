// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spatialid.app.common.constant.ExceptionConstant;
import com.spatialid.app.common.constant.RestApiConstants;
import com.spatialid.app.common.exception.subexception.AccessDeniedException;
import com.spatialid.app.common.exception.subexception.DataRangeException;
import com.spatialid.app.common.exception.subexception.DataUpdateFailureException;
import com.spatialid.app.common.exception.subexception.DuplicateKeyWrapException;
import com.spatialid.app.common.exception.subexception.ExclusiveErrorException;
import com.spatialid.app.common.exception.subexception.InternalApiCallingException;
import com.spatialid.app.common.exception.subexception.NotFoundException;
import com.spatialid.app.common.exception.subexception.ParamErrorException;
import com.spatialid.app.common.exception.subexception.UriNotFoundException;
import com.spatialid.app.common.resource.ErrorResponse;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    private final MessageSource messageSource;
    
    public ApiExceptionHandler(MessageSource messageSource) {
        
        this.messageSource = messageSource;
        
    }

    /**
     * {@link Exception}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, WebRequest request) {
        
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_INTERNAL_SERVER, null, Locale.JAPAN);
        
        ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);

        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link InternalApiCallingException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ InternalApiCallingException.class })
    public ResponseEntity<Object> handleInternalApiCallingException(InternalApiCallingException ex, WebRequest request) {
        
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        final String embeddingStr = ex.getApiName();
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_INTERNAL_SERVER, new String[] {embeddingStr}, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link UriNotFoundException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ UriNotFoundException.class })
    public ResponseEntity<Object> handleUriNotFoundException(UriNotFoundException ex, WebRequest request) {
        
        final HttpStatus status = HttpStatus.NOT_FOUND;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_URI_NOTFOUND, null, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);

    }

    /**
     * {@link NotFoundException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {

        final HttpStatus status = HttpStatus.NOT_FOUND;
        
        final Map<String, String> invalidPathParams = ex.getInvalidPathParams();
        
        final StringBuilder embeddingStrBuilder = new StringBuilder();
        
        final int[] i = {0};
        
        invalidPathParams.forEach((key, value) -> {
                
                if (0 < i[0]) {
                    embeddingStrBuilder.append(",");
                }
                
                embeddingStrBuilder.append(key)
                    .append("=")
                    .append(value);
                
                i[0]++;
                
            });
        
        final String embeddingStr = embeddingStrBuilder.toString();
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_NOTFOUND, new String[] {embeddingStr}, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link ParamErrorException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ ParamErrorException.class })
    public ResponseEntity<Object> handleParamErrorException(ParamErrorException ex, WebRequest request) {
        
        final HttpStatus status = HttpStatus.BAD_REQUEST;
        
        String[] embeddingStr = null;
        
        if (ex.getBr() != null) {
            
            embeddingStr = getEmbedStrFromBr(ex.getBr());
            
        } else if(ex.getEmbeddingMap() != null) {
            
            embeddingStr = getEmbedStrFromMap(ex.getEmbeddingMap());
            
        }
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_BADREQUEST, embeddingStr, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }

    /**
     * {@link DuplicateKeyWrapException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ DuplicateKeyWrapException.class })
    public ResponseEntity<Object> handleDuplicateKeyWrapException(DuplicateKeyWrapException ex, WebRequest request) {

        final HttpStatus status = HttpStatus.CONFLICT;
        
        final String embeddingStr = ex.getDuplicatedKey();
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_CONFLIST, new String[] {embeddingStr}, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link DataUpdateFailureException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ DataUpdateFailureException.class })
    public ResponseEntity<Object> handleDataUpdateFailureException(DataUpdateFailureException ex, WebRequest request) {

        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_UPDATE, null, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link AccessDeniedException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex,
            WebRequest request) {
        
        final HttpStatus status = HttpStatus.FORBIDDEN;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_FORBIDDEN, null, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link DataRangeException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ DataRangeException.class })
    public ResponseEntity<Object> handleDataRangeException(DataRangeException ex,
            WebRequest request) {
        
        final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_NO_DATA, null, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }
    
    /**
     * {@link ExclusiveErrorException}のハンドリングを行う．
     * 
     * @param ex 補足した例外
     * @param request リクエスト情報
     * @return エラーレスポンス
     */
    @ExceptionHandler({ ExclusiveErrorException.class })
    public ResponseEntity<Object> handleExclusiveErrorException(ExclusiveErrorException ex,
            WebRequest request) {
        
        final HttpStatus status = HttpStatus.CONFLICT;
        
        final String message = messageSource.getMessage(ExceptionConstant.KEY_MSG_EXCLUSIVE, null, Locale.JAPAN);
        
        final ErrorResponse errorResponse = createErrorResponse(status.getReasonPhrase(), message);
        
        return super.handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
        
    }

    /**
     * エラーレスポンスオブジェクトを生成する．
     * <p>
     * エラーレスポンスの生成過程で以下の処理を行う．<br>
     * ・HTTPステータスフレーズに接頭辞を付与<br>
     * ・エラー発生日時の取得
     * </p>
     * 
     * @param rawCode 接頭辞のないHTTPステータスフレーズ
     * @param message エラー内容
     * @return {@link ErrorResponse} エラーレスポンスオブジェクト
     */
    private ErrorResponse createErrorResponse(String rawCode, String message) {

        final StringBuilder codeBuilder = new StringBuilder();
        
        final String code = codeBuilder.append(ExceptionConstant.CODE_PREFIX)
            .append(" ")
            .append(rawCode)
            .toString();
        
        final DateTimeFormatter fm = DateTimeFormatter.ofPattern(RestApiConstants.DATE_FORMAT);
        
        final String detail = LocalDateTime.now()
                .format(fm);
        
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .detail(detail)
                .build();
        
    }
    
    /**
     * Beanバリデーションのエラーレスポンスで使用する埋め込み文字を作成する．
     * <p>
     * {@link BindingResult}からバリデーション違反となったパラメータの情報を抽出する．
     * </p>
     * 
     * @param br バリデーション結果
     * @return 埋め込み文字を格納した配列
     */
    private String[] getEmbedStrFromBr(BindingResult br) {
        
        final String[] embeddingStr = new String[2];
        
        final StringBuilder violationKeyBuilder = new StringBuilder();
        
        final StringBuilder violationPairBuilder = new StringBuilder();
        
        int i = 0;
        
        for (FieldError fieldError : br.getFieldErrors()) {

            if (0 < i) {
                
                violationKeyBuilder.append(",");
                
                violationPairBuilder.append(",");
                
            }
            
            violationKeyBuilder.append(fieldError.getField());
            
            violationPairBuilder.append(fieldError.getField())
                .append("=")
                .append(fieldError.getRejectedValue());
            
            i++;
            
            }        
        
        embeddingStr[0] = violationKeyBuilder.toString();
        
        embeddingStr[1] = violationPairBuilder.toString();
        
        return embeddingStr;
        
    }
    
    /**
     * パスパラメータのエラーレスポンスで使用する埋め込み文字を作成する．
     * <p>
     * {@link Map}からバリデーション違反となったパスパラメータの情報を抽出する．
     * </p>
     * 
     * @param embeddingMap バリデーション結果
     * @return 埋め込み文字を格納した配列
     */
    private String[] getEmbedStrFromMap(Map<String, String> embeddingMap) {
        
        final String[] embeddingStr = new String[2];
        
        final StringBuilder violationKeyBuilder = new StringBuilder();
        
        final StringBuilder violationPairBuilder = new StringBuilder();
        
        int i = 0;
        
        for (Entry<String, String> entry : embeddingMap.entrySet()) {

            if (0 < i) {
                
                violationKeyBuilder.append(",");
                
                violationPairBuilder.append(",");
                
            }
            
            violationKeyBuilder.append(entry.getKey());
            
            violationPairBuilder.append(entry.getKey())
                .append("=")
                .append(entry.getValue());
            
            i++;
            
            }        
        
        embeddingStr[0] = violationKeyBuilder.toString();
        
        embeddingStr[1] = violationPairBuilder.toString();
        
        return embeddingStr;
        
    }
    
}