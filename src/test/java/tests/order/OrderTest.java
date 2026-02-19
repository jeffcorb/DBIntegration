package tests.order;

import base.BaseTest;
import db.DBUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    @Test
    @Tag("DBIntegration")
    @DisplayName("DELETE - Should delete existing order")
    void shouldDeleteOrder() throws Exception {

        int rows = DBUtils.executeUpdate(
                "DELETE FROM orders WHERE id = ?",
                1
        );

        assertEquals(1, rows);

        List<?> result =
                DBUtils.executeQuery(
                        "SELECT * FROM orders WHERE id = ?",
                        1
                );

        assertTrue(result.isEmpty());
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("DELETE - Should return 0 rows when order does not exist")
    void shouldReturnZeroWhenOrderNotFound() throws Exception {

        int rows = DBUtils.executeUpdate(
                "DELETE FROM orders WHERE id = ?",
                999
        );

        assertEquals(0, rows);
    }
}