package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    public static int executeUpdate(String sql, Object... params) throws Exception {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setParameters(ps, params);

            return ps.executeUpdate();
        }
    }

    public static List<Map<String, Object>> executeQuery(String sql, Object... params) throws Exception {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            setParameters(ps, params);

            ResultSet rs = ps.executeQuery();

            return mapResultSet(rs);
        }
    }

    private static void setParameters(PreparedStatement ps, Object... params) throws Exception {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    private static List<Map<String, Object>> mapResultSet(ResultSet rs) throws Exception {

        List<Map<String, Object>> results = new ArrayList<>();

        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (rs.next()) {

            Map<String, Object> row = new HashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                row.put(columnName, rs.getObject(i));
            }

            results.add(row);
        }

        return results;
    }
}
