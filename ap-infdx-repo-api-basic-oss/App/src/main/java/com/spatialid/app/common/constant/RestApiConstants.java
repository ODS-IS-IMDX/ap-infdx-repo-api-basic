// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.constant;

/**
 * REST API 定数クラス<br>
 * 
 * @author btimaimsk
 * @version 1.00
 */
public class RestApiConstants {
   
    /** 各APIで共通して日付フォーマットとして用いる定数 */
    public static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";

    /** 日付形式チェックで用いる日付フォーマット */
    public static final String STRICT_DATE_FORMAT = "uuuu/MM/dd HH:mm:ss";
    
    /** ミリ秒まで対応した日付フォーマット */
    public static final String HIGH_ACCURACY_DATE_FORMAT = "uuuu/MM/dd HH:mm:ss.SSSSSS";
    
    /** 利用システムID */
    public static final String SERVICER_ID = "servicerId";

    /** タスク状況:0 未処理 */
    public static final String TASK_STATUS_UNTREATED = "0";

    /** タスク状況:1 処理中 */
    public static final String TASK_STATUS_PROCESSING = "1";

    /** タスク状況:2 完了 */
    public static final String TASK_STATUS_COMPLETED = "2";

    /** タスク状況:9 エラー */
    public static final String TASK_STATUS_ERROR = "9";

    /** dateModelType設定値"test1" */
    public static final String TEST1 = "test1";

    /** processClass設定値"01" */
    public static final String PROCESSCLASS = "01";

    /** S3URL接頭辞 */
    public static final String S3PATH = "s3://";

    /** URLパス"/" */
    public static final String SLASH = "/";

}