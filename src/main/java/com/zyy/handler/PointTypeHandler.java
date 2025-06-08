package com.zyy.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.WKTWriter;
import java.sql.*;

@MappedTypes(Point.class)
public class PointTypeHandler extends BaseTypeHandler<Point> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Point point, JdbcType jdbcType) throws SQLException {
        ps.setObject(i, pointToEWKT(point));
    }

    @Override
    public Point getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ewktToPoint(rs.getString(columnName));
    }

    @Override
    public Point getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ewktToPoint(rs.getString(columnIndex));
    }

    @Override
    public Point getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return ewktToPoint(cs.getString(columnIndex));
    }

    // 将 JTS Point 转换为 EWKT 字符串
    private String pointToEWKT(Point point) {
        return "SRID=4326;POINT(" + point.getX() + " " + point.getY() + ")";
    }

    // 将 EWKT 字符串转换为 JTS Point
    private Point ewktToPoint(String ewkt) {
        if (ewkt == null) return null;
        try {
            // 移除 "SRID=4326;" 前缀
            String wkt = ewkt.replaceFirst("^SRID=\\d+;", "");
            return (Point) new WKTReader().read(wkt);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse EWKT: " + ewkt, e);
        }
    }
}
