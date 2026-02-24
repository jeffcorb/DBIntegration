package db;

public class DatabaseInitializer {
    public static void main(String[] args) throws Exception {
        DatabaseSetup.initializeDatabase();
        System.out.println("Database created and seeded successfully.");
    }
}
