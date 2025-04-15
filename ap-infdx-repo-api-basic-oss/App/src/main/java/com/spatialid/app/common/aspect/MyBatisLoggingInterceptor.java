// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.aspect;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

/**
 * MyBatisのログ出力を行うクラス.<br>
 * <p>
 * MyBatisの処理の前後にログ出力を行う処理を実装する<br>
 * </p>
 *
 * @author kawashima naoya
 * @version 1.0
 */
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }) })
@Component
@Log4j2
public class MyBatisLoggingInterceptor implements Interceptor {

    /**
     * ログ出力:実行時間、実行メソッド
     */
    private static final String LOGGER_METHOD_NAME = "実行時間: {} ms. 実行メソッド: {}";

    /**
     * ログ出力:SQL
     */
    private static final String LOGGER_PREPARING = "Preparing: {}";

    /**
     * MyBatisのログ出力を行うクラス初期値設定処理.<br>
     * <p>
     * MyBatisのログ出力を行うクラスのパラメータを初期化する処理を行う.<br>
     * </p>
     */
    public MyBatisLoggingInterceptor() {
    }

    /**
     * SQL実行前後に処理を追加する
     *
     * @param invocation メソッド呼出用オブジェクト
     * @return SQL実行メソッドの戻り値
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 返却オブジェクトを定義
        Object returnObj = null;

        // SQL実行メソッドを実行し、その開始・終了時刻も取得
        long start = System.currentTimeMillis();
        returnObj = invocation.proceed();
        long end = System.currentTimeMillis();

        // SQL実行時間、実行メソッド名(フルパス)を出力
        MappedStatement statement = (MappedStatement) invocation.getArgs()[0];
        String[] items = statement.getId().split("\\.");
        log.trace(LOGGER_METHOD_NAME, end - start, String.join(".", items));

        // SQLログを出力
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = statement.getBoundSql(parameter);

        log.debug(LOGGER_PREPARING, boundSql.getSql());

        return returnObj;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
