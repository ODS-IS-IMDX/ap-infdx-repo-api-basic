// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.aspect;

import java.util.Locale;

import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.spatialid.app.common.constant.LoggingConstant;
import com.spatialid.app.common.exception.ErrorException;
import com.spatialid.app.common.exception.InfoException;
import com.spatialid.app.common.exception.UserFaultException;
import com.spatialid.app.common.exception.WarnException;
import lombok.extern.log4j.Log4j2;

/**
 * ロギング機能を定義したクラス．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/04
 */
@Log4j2
@Aspect
@Component
public class LoggingAspect {

    /**
     * メッセージソースクラス.
     */
    private final MessageSource messageSource;
    
    public LoggingAspect(MessageSource messageSource) {
        
        this.messageSource = messageSource;
        
    }

    /**
     * 全コントローラークラス・アクセス制御インターセプタのメソッドに対して、ロギング処理を行う．
     * 
     * @param joinPoint {@link ProceedingJoinPoint} 対象メソッドの情報を保持、または操作するオブジェクト
     * @return 対象メソッドの返却値
     * @throws Throwable 対象メソッドから送出された例外
     */
    @Around("execution(* com.spatialid.app.api.presentation.*.*Controller.*(..)) || execution(* com.spatialid.app.api.presentation.*.*Interceptor.*(..)) || execution(* com.spatialid.app.common.accesshistory.service.FilterDeligate*.*(..))")
    public Object controllerMonitor(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = null;
        
        // 開始ログ出力
        outputInfoLog("API_LOG_INFO0002", joinPoint);

        long start = System.currentTimeMillis(); // メソッド開始前のシステム時刻
        
        try {

            proceed = joinPoint.proceed(); // メソッド実行

        } catch (ErrorException ex) {
            
            log.error(messageSource.getMessage(ex.getMsgKey(), ex.getEmbeddingStrs(), Locale.JAPAN), ex);
            
            throw ex;
        
        } catch (WarnException ex) {
            
            log.warn(messageSource.getMessage(ex.getMsgKey(), ex.getEmbeddingStrs(), Locale.JAPAN), ex);
            
            throw ex;
            
        } catch(InfoException ex) {
            
            log.info(messageSource.getMessage(ex.getMsgKey(), ex.getEmbeddingStrs(), Locale.JAPAN));
            
            throw ex;
            
        } catch(UserFaultException ex) {
            
            throw ex;
            
        } catch(Exception ex) {
            
            log.error(messageSource.getMessage(LoggingConstant.KEY_ERROR_MSG, new String[] { ex.getMessage() }, Locale.JAPAN), ex);
            
            throw ex;
            
        } finally {

            long end = System.currentTimeMillis(); // メソッド終了後のシステム時刻
            // 終了ログ出力
            outputInfoLog("API_LOG_INFO0003", joinPoint, (end - start));
        }

        return proceed; // メソッドの内容を返却
    }


    /**
     * インフォメーションログ.<br>
     * <p>
     * インフォメーションログを出力する
     * </p>
     *
     * @param code      ログID
     * @param joinPoint 処理情報
     */
    private void outputInfoLog(String code, JoinPoint joinPoint) {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        String methodArgsNames = "";
        // 引数があれば
        if (ObjectUtils.isNotEmpty(joinPoint.getArgs())) {
            methodArgsNames = argsToString(joinPoint);
        }

        String logMessage = messageSource.getMessage(code, new Object[] { className, methodName, methodArgsNames },
                Locale.JAPAN);
        log.info(logMessage);
    }

    /**
     * インフォメーションログ.<br>
     * <p>
     * インフォメーションログを出力する
     * </p>
     *
     * @param code      ログID
     * @param joinPoint 処理情報
     * @param time      処理時間
     */
    private void outputInfoLog(String code, JoinPoint joinPoint, long time) {

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        String logMessage = messageSource.getMessage(code, new Object[] { className, methodName, time }, Locale.JAPAN);

        log.info(logMessage);
    }

    /**
     * パラメータ文字列生成<br>
     *
     * @param joinPoint 処理情報
     */
    private String argsToString(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        sb.append("(");

        String[] parameterNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        Object[] parameterValues = joinPoint.getArgs();

        for (int i = 0; i < parameterValues.length; i++) {
            if (0 < i) {
                sb.append(", ");
            }
            sb.append(parameterNames[i]).append("=");
            sb.append(parameterValues[i]);
        }
        sb.append(")");

        return sb.toString();
    }
}