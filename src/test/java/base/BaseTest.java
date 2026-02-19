package base;

import db.DatabaseSetup;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    static void setupDatabase() throws Exception {
        DatabaseSetup.initializeDatabase();
    }
}