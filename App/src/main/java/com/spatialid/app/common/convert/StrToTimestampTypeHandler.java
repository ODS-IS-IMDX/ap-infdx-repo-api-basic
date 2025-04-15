// © 2025 NTT DATA Japan Co., Ltd. & NTT InfraNet All Rights Reserved.

package com.spatialid.app.common.convert;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.spatialid.app.common.constant.RestApiConstants;

/**
 * MyBatisにおけるTypeHandlerを定義するクラス．<br>
 * String型をTimestampに変換する．
 * 
 * @author matsumoto kentaro
 * @version 1.1 2024/10/08
 *
 */
public class StrToTimestampTypeHandler extends BaseTypeHandler<String> {
    
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(RestApiConstants.DATE_FORMAT);
        
    /**
     * Javaから渡されたString型を、DB側のTimestampにマッピングする．
     * <p>
     * String型がnullでなかった場合に、実行される．
     * </p>
     * 
     * @param ps MyBatisから提供された{@link PreparedStatement}オブジェクト
     * @param i MyBatisから提供された引数のインデックス
     * @param parameter Java側から渡されたString型
     * @param jdbcType MyBatisから提供された{@link JdbcType}
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
    
        final LocalDateTime localDateTime =LocalDateTime.parse(parameter, FORMATTER);
        
        ps.setTimestamp(i, Timestamp.valueOf(localDateTime));
        
    }
    
    /**
     * DBから渡されたTimestamp型を、Java側のString型に変換する．
     * <p>
     * 指定されたカラム名を元に、データを取得する．
     * </p>
     * 
     * @param rs MyBatisから提供された{@link ResultSet}オブジェクト
     * @param columnName MyBatisから提供されたデータを取得する対象カラム名
     * @return javaにおけるString型
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
            
        final Timestamp rawTimestamp = rs.getTimestamp(columnName);
        
        return convertTimestampToStr(rawTimestamp);
        
    }
    
    /**
     * DBから渡されたTimestamp型を、Java側のString型に変換する．
     * <p>
     * 指定されたカラムのインデックスを元に、データを取得する．
     * </p>
     * 
     * @param rs MyBatisから提供された{@link ResultSet}オブジェクト
     * @param columnIndex MyBatisから提供されたデータを取得する対象カラムインデックス
     * @return javaにおけるString型
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        
        final Timestamp rawTimestamp = rs.getTimestamp(columnIndex);
        
        return convertTimestampToStr(rawTimestamp);
        
    }
    
    /**
     * DBから渡されたTimestamp型を、Java側のString型に変換する．
     * <p>
     * ストアドプロシージャからカラムのインデックスを指定してデータを取得する．
     * </p>
     * 
     * @param cs MyBatisから提供された{@link CallableStatement}オブジェクト
     * @param columnIndex MyBatisから提供されたデータを取得する対象カラムインデックス
     * @return javaにおけるString型
     * @exception SQLException DB側で異常が起こった場合
     */
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        
        final Timestamp rawTimestamp = cs.getTimestamp(columnIndex);
        
        return convertTimestampToStr(rawTimestamp);
        
    }
    
    /**
     * 引数として受け取ったTimestamp型をString型に変換して返却する．
     * 
     * @param rawTimestamp DBにおけるTimestamp型
     * @return String型に変換された更新日時
     * @throws SQLException DB側で異常が起こった場合
     */
    private String convertTimestampToStr(Timestamp rawTimestamp) throws SQLException {
        
        try {
            
            final String timestamp = rawTimestamp.toLocalDateTime().format(FORMATTER);
            
            return timestamp;
            
        } catch (Exception e) {
            
            throw new SQLException(e);
            
        }
        
    }

}
