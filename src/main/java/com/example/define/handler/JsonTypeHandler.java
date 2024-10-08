package com.example.define.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;
import java.util.List;

public class JsonTypeHandler extends BaseTypeHandler<List<String>> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, toJson(parameter)); // List<String>을 JSON으로 변환하여 저장
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return toObject(rs.getString(columnName)); // ResultSet에서 JSON 문자열을 읽어 List<String>으로 변환
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return toObject(rs.getString(columnIndex)); // ResultSet에서 인덱스로 JSON 문자열을 읽어 List<String>으로 변환
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return toObject(cs.getString(columnIndex)); // 저장 프로시저 호출 결과를 처리
    }

    private String toJson(List<String> object) throws SQLException {
        try {
            return mapper.writeValueAsString(object); // List<String>을 JSON 문자열로 변환
        } catch (Exception e) {
            throw new SQLException("Failed to convert List to JSON string.", e);
        }
    }

    private List<String> toObject(String content) throws SQLException {
        try {
            return mapper.readValue(content, new TypeReference<List<String>>() {}); // JSON 문자열을 List<String>으로 변환
        } catch (Exception e) {
            throw new SQLException("Failed to convert JSON string to List.", e);
        }
    }
}

