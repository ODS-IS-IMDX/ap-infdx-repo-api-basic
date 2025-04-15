// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.constant;

/**
 * ログに出力するメッセージのキー名を定数として定義したクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
public class LoggingConstant {
    
    /**
     * アプリケーション起動ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_BOOT = "API_LOG_INFO0001";
    
    /**
     * 処理開始ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_START = "API_LOG_INFO0002";
    
    /**
     * 処理終了ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_END = "API_LOG_INFO0003";
    
    /**
     * データ整備範囲外の例外ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_DATA_RANGE = "API_LOG_INFO0004";
    
    /**
     * データ更新中の例外ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_DATA_UPDATE = "API_LOG_INFO0005";
    
    /**
     * 処理速度ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_MEASURE = "API_LOG_INFO0006";
    
    /**
     * 排他制御の例外ログに対応したキー名．
     */
    public static final String KEY_INFO_MSG_EXCLUSIVE = "API_LOG_INFO0007";
    
    /**
     * 汎用エラーログに対応したキー名．
     */
    public static final String KEY_ERROR_MSG = "API_LOG_ERROR0001";
    
    /**
     * API呼び出し失敗の例外ログに対応したキー名．
     */
    public static final String KEY_ERROR_MSG_API_CALLING = "API_LOG_ERROR0002";
    
    /**
     * 不正リクエストヘッダログに対応したキー名．
     */
    public static final String KEY_WARN_MSG_HEADER = "API_LOG_WARN0001";
    
    /**
     * トークン検証失敗ログに対応したキー名．
     */
    public static final String KEY_WARN_MSG_INVALID_TOKEN = "API_LOG_WARN0002";
    
    /**
     * 利用者システムID欠落ログに対応したキー名．
     */
    public static final String KEY_WARN_MSG_MISSING_ID = "API_LOG_WARN0003";
    
    /**
     * アクセス履歴登録(更新)の失敗ログに対応したキー名．
     */
    public static final String KEY_WARN_MSG_FAILED_UPDATE_ACCESS_HISTORY = "API_LOG_WARN0004";
    
}
