// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.util;

import java.lang.reflect.Array;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 共通ユーティリティクラス.<br>
 * 
 */
public class CommonUtils {

    /**
     * 引数文字列の有効性を判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列がnullでなくかつ空文字でない場合、true.
     */
    public static boolean isValid(String inStr) {
        if (StringUtils.isEmpty(inStr)) {
            return false;
        }
        return true;
    }

    /**
     * 引数文字列の無効性を判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列がnullまたは空文字の場合、true.
     */
    public static boolean isInvalid(String inStr) {

        return (inStr == null || "".equals(inStr));
    }

    /**
     * 引数文字列が整数か判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列が整数の場合、true.
     */
    public static boolean checkRegex(String inStr) {

        return checkPattern(inStr, "^[0-9]+$|-[0-9]+$");
    }

    /**
     * 引数文字列が正の整数か判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列が正の整数の場合、true.
     */
    public static boolean checkPositiveNumber(String inStr) {

        return checkPattern(inStr, "^[0-9]+$");
    }

    /**
     * 引数文字列が正規表現と合致するか判定する.<br>
     *
     * @param inStr   判定文字列
     * @param pattern 正規表現パターン
     * @return 検査文字列がnullまたは空文字の場合、true.
     */
    public static boolean checkPattern(String inStr, String pattern) {

        // 正規表現チェック
        Pattern ptn = Pattern.compile(pattern);
        return ptn.matcher(inStr).matches();
    }

    /**
     * 引数文字列が1以上の正の整数か判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列が1以上の正の整数の場合、true.
     */
    public static boolean checkWholeNumber(String inStr) {

        return checkPattern(inStr, "^[1-9]\\d*$");

    }

    /**
     * 引数文字列がタイムスタンプ形式と合致するか判定する.<br>
     *
     * @param inStr 検査文字列
     * @return 検査文字列がタイムスタンプ形式に合致した場合、true.
     */
    public static boolean checkTimestamp(String inStr) {

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
            formatter.parse(inStr);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

    /**
     * 引数文字列が数値か判定する.<br>
     *
     * @param inStr     検査文字列
     * @param valueName 検査文字列論理名
     * @return 検査文字列が正の整数の場合、true.
     */
    public static boolean checkNumber(String inStr) {

        if (!(checkPattern(inStr, "^[+,-]?([1-9]\\d*|0)(\\.\\d+)?$"))) {
            return false;
        }
        return true;
    }

    /**
     * 検査文字列が最小桁数と最大桁数に合致するか判定する.<br>
     *
     * @param inStr     検査文字列
     * @param min       最小桁数
     * @param max       最大桁数
     * @param valueName 検査文字列論理名
     * @return 検査文字列が最小桁数と最大桁数に合致した場合、true.
     */
    public static boolean checkLength(String inStr, int min, int max) {

        if ((inStr != null && inStr.length() >= min && inStr.length() <= max) == false) {
            return false;
        }
        return true;
    }
    
    /**
     * アップキャストされているObject型について空であるかを検査する．
     * <p>
     * isEmpty(null) = true<br>
     * isEmpty("") = true<br>
     * isEmpty(new Collection<?>()) = true<br>
     * isEmpty(new Array[]) = true
     * </p>
     * 
     * @param obj アップキャストされたオブジェクト
     * @return Objectが空であるかどうかを示す真偽値
     */
    public static boolean isEmpty(Object obj) {

        if (obj == null) {
            
            return true;
        
        } else if (obj instanceof String) {
            
            return StringUtils.isEmpty((String) obj);
            
        } else if (obj instanceof Collection<?>) {
            
            return CollectionUtils.isEmpty((Collection<?>) obj);
            
        } else if (obj.getClass().isArray()) {
            
            return (Array.getLength(obj) == 0);
        
        } else {
            
            return (obj == null);
            
        }
        
    }
    
}
