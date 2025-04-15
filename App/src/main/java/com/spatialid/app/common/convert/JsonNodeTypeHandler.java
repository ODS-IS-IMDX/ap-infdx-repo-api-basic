// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.convert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jsonbをJsonNodeに変換するカスタムタイプハンドラー.
 * 
 * @Auther ukai jun
 * @Version 1.1 2024/09/12
 */
public class JsonNodeTypeHandler extends BaseTypeHandler<JsonNode> {

    /**
     * オブジェクトマッパー.
     */
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * PreparedStatementに非nullのパラメータを設定するメソッド.
     *
     * @param ps        パラメータを設定するためのインスタンス.
     * @param i         パラメータのインデックス.
     * @param parameter 設定するJsonNode.
     * @param jdbcType  パラメータのJDBC型.
     * @throws SQLException データベースアクセスエラーが発生した場合.
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType)
            throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.OTHER);
        } else {
            ps.setString(i, parameter.toString());
        }
    }

    /**
     * ResultSetからカラム名を指定して、nullableな結果を取得するメソッド.
     *
     * @param rs         結果を取得するResultSet.
     * @param columnName カラム名.
     * @return ResultSetから取得したJsonNode.
     * @throws SQLException データベースアクセスエラーが発生した場合.
     */
    @Override
    public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String json = rs.getString(columnName);
        return convertToJsonNode(json);
    }

    /**
     * ResultSetからカラムインデックスを指定して、nullableな結果を取得するメソッド.
     *
     * @param rs          結果を取得するResultSet.
     * @param columnIndex カラムインデックス.
     * @return ResultSetから取得したJsonNode.
     * @throws SQLException データベースアクセスエラーが発生した場合.
     */
    @Override
    public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String json = rs.getString(columnIndex);
        return convertToJsonNode(json);
    }

    /**
     * CallableStatementからカラムインデックスを指定して、nullableな結果を取得するメソッド.
     *
     * @param cs          結果を取得するCallableStatement.
     * @param columnIndex カラムインデックス.
     * @return CallableStatementから取得したJsonNode.
     * @throws SQLException データベースアクセスエラーが発生した場合.
     */
    @Override
    public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String json = cs.getString(columnIndex);
        return convertToJsonNode(json);
    }

    /**
     * JSON文字列をJsonNodeに変換するメソッド.
     *
     * @param json JSON文字列.
     * @return JSON文字列のJsonNode表現.
     * @throws SQLException JSON変換中にエラーが発生した場合.
     */
    private JsonNode convertToJsonNode(String json) throws SQLException {
        if (json != null) {
            try {
                return objectMapper.readTree(json);
            } catch (Exception e) {
                throw new SQLException("JSONのパースに失敗しました", e);
            }
        }
        return null;
    }
}
