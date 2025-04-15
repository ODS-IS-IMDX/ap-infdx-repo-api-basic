// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.convert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * MyBatisにおけるTypeHandlerを定義するクラス．<br>
 * Map型をJsonbに変換する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/08
 */
public class MapToJsonbTypeHandler extends BaseTypeHandler<Map<String, String>> {
    
    /**
     * Jsonオブジェクトマッパー．
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
        
    /**
     * Javaから渡されたMap型を、DB側のJsonbにマッピングする．
     * <p>
     * Map型がnullでなかった場合に、実行される．
     * </p>
     * 
     * @param ps MyBatisから提供された{@link PreparedStatement}オブジェクト
     * @param i MyBatisから提供された引数のインデックス
     * @param parameter Java側から渡されたMap型
     * @param jdbcType MyBatisから提供された{@link JdbcType}
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, String> parameter, JdbcType jdbcType) throws SQLException {
        
        try {
            
            final String json = objectMapper.writeValueAsString(parameter);
            
            ps.setString(i, json);
            
        } catch (JsonProcessingException e) {
            
            throw new SQLException(e);
            
        }
                
    }
    
    /**
     * DBから渡されたJsonb型を、Java側のMap型に変換する．
     * <p>
     * 指定されたカラム名を元に、データを取得する．
     * </p>
     * 
     * @param rs MyBatisから提供された{@link ResultSet}オブジェクト
     * @param columnName MyBatisから提供されたデータを取得する対象カラム名
     * @return javaにおけるMap型
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public Map<String, String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
            
        final String json = rs.getString(columnName);
        
        return getMapFromJson(json);
        
    }
    
    /**
     * DBから渡されたJsonb型を、Java側のMap型に変換する．
     * <p>
     * 指定されたカラムのインデックスを元に、データを取得する．
     * </p>
     * 
     * @param rs MyBatisから提供された{@link ResultSet}オブジェクト
     * @param columnIndex MyBatisから提供されたデータを取得する対象カラムインデックス
     * @return javaにおけるMap型
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public Map<String, String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        
        final String json = rs.getString(columnIndex);
        
        return getMapFromJson(json);
        
    }
    
    /**
     * DBから渡された文字列型の二次元配列を、Java側の二次元配列に変換する．
     * <p>
     * ストアドプロシージャからカラムのインデックスを指定してデータを取得する．
     * </p>
     * 
     * @param cs MyBatisから提供された{@link CallableStatement}オブジェクト
     * @param columnIndex MyBatisから提供されたデータを取得する対象カラムインデックス
     * @return javaにおける文字列型の二次元配列
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public Map<String, String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        final String json = cs.getString(columnIndex);
        
        return getMapFromJson(json);
        
    }
    
    /**
     * 引数として受け取ったjson文字列をMap型に変換して返却する．
     * 
     * @param json json文字列
     * @return Map型に変換されたJson文字列
     * @throws SQLException DB側で異常が起こった場合
     */
    private Map<String, String> getMapFromJson(String json) throws SQLException {
        
        if (json == null || json.isEmpty()) {
            
            return null;
            
        }
        
        try {
            
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>(){});
            
        } catch (JsonProcessingException e) {
            
            throw new SQLException(e);
            
        }
        
    }

}
