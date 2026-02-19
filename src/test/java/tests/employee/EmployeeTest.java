package tests.employee;

import base.BaseTest;
import db.DBUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest extends BaseTest {

    @Test
    @Tag("DBIntegration")
    @DisplayName("CREATE & READ - Should insert and retrieve employee")
    void shouldCreateAndReadEmployee() throws Exception {

        int rows = DBUtils.executeUpdate("""
                    INSERT INTO employees (first_name, last_name, role, status)
                    VALUES (?, ?, ?, ?)
                """, "Laura", "Gomez", "SUPPORT", "ACTIVE");

        assertEquals(1, rows);

        List<Map<String, Object>> result =
                DBUtils.executeQuery(
                        "SELECT * FROM employees WHERE first_name = ?",
                        "Laura"
                );

        assertEquals(1, result.size());
        assertEquals("SUPPORT", result.get(0).get("ROLE"));
    }

    @Test
    @Tag("DBIntegration")
    @DisplayName("UPDATE - Should update employee status")
    void shouldUpdateEmployeeStatus() throws Exception {

        int rows = DBUtils.executeUpdate(
                "UPDATE employees SET status = ? WHERE first_name = ?",
                "INACTIVE",
                "Ana"
        );

        assertEquals(1, rows);
    }
}