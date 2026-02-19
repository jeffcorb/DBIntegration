package tests.customer;

import base.BaseTest;
import db.DBUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest extends BaseTest {

    @Test
    @Tag("DBIntegration")
    @DisplayName("CREATE - Should insert new customer successfully")
    void shouldCreateCustomer() throws Exception {

        String sql = """
                    INSERT INTO customers (first_name, last_name, email, status)
                    VALUES (?, ?, ?, ?)
                """;

        int rows = DBUtils.executeUpdate(
                sql,
                "Mario",
                "Rossi",
                "mario@test.com",
                "ACTIVE"
        );

        assertEquals(1, rows);

        List<Map<String, Object>> result =
                DBUtils.executeQuery(
                        "SELECT * FROM customers WHERE email = ?",
                        "mario@test.com"
                );

        assertEquals(1, result.size());
        assertEquals("Mario", result.get(0).get("FIRST_NAME"));
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("READ - Should retrieve existing customer")
    void shouldReadCustomer() throws Exception {

        List<Map<String, Object>> result =
                DBUtils.executeQuery(
                        "SELECT * FROM customers WHERE email = ?",
                        "john@test.com"
                );

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).get("FIRST_NAME"));
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("UPDATE - Should update customer status")
    void shouldUpdateCustomer() throws Exception {

        int rows = DBUtils.executeUpdate(
                "UPDATE customers SET status = ? WHERE email = ?",
                "INACTIVE",
                "john@test.com"
        );

        assertEquals(1, rows);

        List<Map<String, Object>> result =
                DBUtils.executeQuery(
                        "SELECT status FROM customers WHERE email = ?",
                        "john@test.com"
                );

        assertEquals("INACTIVE", result.get(0).get("STATUS"));
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("NEGATIVE - Should fail when inserting duplicate email")
    void shouldFailOnDuplicateEmail() {

        String sql = """
                    INSERT INTO customers (first_name, last_name, email, status)
                    VALUES (?, ?, ?, ?)
                """;

        assertThrows(Exception.class, () ->
                DBUtils.executeUpdate(
                        sql,
                        "Duplicate",
                        "User",
                        "john@test.com", // ya existe
                        "ACTIVE"
                )
        );
    }
}