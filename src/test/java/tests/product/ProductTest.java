package tests.product;

import base.BaseTest;
import db.DBUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductTest extends BaseTest {

    @Test
    @Tag("DBIntegration")
    @DisplayName("CREATE & READ - Should insert and retrieve product")
    void shouldCreateAndReadProduct() throws Exception {

        int rows = DBUtils.executeUpdate("""
                    INSERT INTO products (name, price, stock_quantity, status)
                    VALUES (?, ?, ?, ?)
                """, "Monitor", 300.00, 20, "AVAILABLE");

        assertEquals(1, rows);

        List<Map<String, Object>> result =
                DBUtils.executeQuery(
                        "SELECT * FROM products WHERE name = ?",
                        "Monitor"
                );

        assertEquals(1, result.size());
        assertEquals(new BigDecimal("300.00"), result.get(0).get("PRICE"));
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("NEGATIVE - Should fail when inserting negative price")
    void shouldFailWhenPriceIsNegative() {

        assertThrows(Exception.class, () ->
                DBUtils.executeUpdate("""
                            INSERT INTO products (name, price, stock_quantity, status)
                            VALUES (?, ?, ?, ?)
                        """, "InvalidProduct", -50.00, 10, "AVAILABLE")
        );
    }
}