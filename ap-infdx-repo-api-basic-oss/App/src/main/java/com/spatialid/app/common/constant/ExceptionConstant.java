// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.constant;

/**
 * 例外ハンドリングに使用する定数を定義したクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/02
 */
public final class ExceptionConstant {
    
    /**
     * エラー概要の先頭に付与する接頭辞．
     */
    public static final String CODE_PREFIX = "[infra-dx]";
    
    /**
     * 400エラー発生時に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_BADREQUEST = "error.api.badrequest";
    
    /**
     * 404エラー発生時に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_NOTFOUND = "error.api.notfound";
    
    /**
     * 409エラー発生時に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_CONFLIST = "error.api.conflict";
    
    /**
     * 500エラー発生時に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_INTERNAL_SERVER = "error.api.internalservererror";
    
    /**
     * 403エラー発生時に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_FORBIDDEN = "error.api.forbidden";
    
    /**
     * アシュアランスレベルの設定が存在しない場合に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_ASSURANCE = "error.api.assuranceerror";
    
    /**
     * 対象データが更新されていた場合に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_EXCLUSIVE = "error.api.exclusiveerror";
    
    /**
     * トークンの検証に失敗した場合に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_TOKEN = "error.api.forbidden.tokenerror";
    
    /**
     * データ整備範囲外の領域が指定された場合に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_NO_DATA = "error.api.nodataerror";
    
    /**
     * 対象データが更新中の場合に返却されるメッセージに対応したキー名
     */
    public static final String KEY_MSG_UPDATE = "error.api.dataupdateerror";
    
    /**
     * リクエストURIが存在しない場合に返却されるメッセージに対応したキー名．
     */
    public static final String KEY_MSG_URI_NOTFOUND = "error.api.urinotfound";
    
}
