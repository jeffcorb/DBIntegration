package base;

import org.junit.jupiter.api.BeforeAll;

import java.io.File;

public abstract class BaseTest {

    @BeforeAll
    static void validateDatabaseExists() {
        File dbFile = new File("dbintegration.mv.db");
        if (!dbFile.exists()) {
            throw new RuntimeException(
                    "Database not initialized. Please run DatabaseInitializer first."
            );
        }
    }
}