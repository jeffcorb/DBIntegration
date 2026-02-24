package db;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() throws Exception {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // CREATE TABLES

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS customers (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            first_name VARCHAR(50) NOT NULL,
                            last_name VARCHAR(50) NOT NULL,
                            email VARCHAR(100) UNIQUE NOT NULL,
                            status VARCHAR(20),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                        );
                    """);

            // Employees
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS employees (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            first_name VARCHAR(50) NOT NULL,
                            last_name VARCHAR(50) NOT NULL,
                            role VARCHAR(20),
                            status VARCHAR(20),
                            hired_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                        );
                    """);

            // Products
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS products (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            price DECIMAL(10,2) CHECK (price > 0),
                            stock_quantity INT CHECK (stock_quantity >= 0),
                            status VARCHAR(20)
                        );
                    """);

            // Orders
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS orders (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            customer_id INT,
                            employee_id INT,
                            product_id INT,
                            quantity INT CHECK (quantity > 0),
                            total_amount DECIMAL(10,2) CHECK (total_amount > 0),
                            status VARCHAR(20),
                            order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (customer_id) REFERENCES customers(id),
                            FOREIGN KEY (employee_id) REFERENCES employees(id),
                            FOREIGN KEY (product_id) REFERENCES products(id)
                        );
                    """);
            //INSERT DATA

            stmt.execute("""
                        INSERT INTO customers (first_name, last_name, email, status)
                        VALUES 
                        ('John', 'Doe', 'john@test.com', 'ACTIVE'),
                        ('Jane', 'Smith', 'jane@test.com', 'ACTIVE'),
                        ('Carlos', 'Lopez', 'carlos@test.com', 'INACTIVE');
                    """);

            stmt.execute("""
                        INSERT INTO employees (first_name, last_name, role, status)
                        VALUES 
                        ('Ana', 'Martinez', 'SALES', 'ACTIVE'),
                        ('Luis', 'Ramirez', 'ADMIN', 'ACTIVE');
                    """);

            stmt.execute("""
                        INSERT INTO products (name, price, stock_quantity, status)
                        VALUES 
                        ('Laptop', 1200.00, 10, 'AVAILABLE'),
                        ('Mouse', 25.50, 100, 'AVAILABLE'),
                        ('Keyboard', 75.00, 50, 'AVAILABLE');
                    """);

            stmt.execute("""
                        INSERT INTO orders (customer_id, employee_id, product_id, quantity, total_amount, status)
                        VALUES 
                        (1, 1, 1, 1, 1200.00, 'PENDING'),
                        (2, 2, 2, 2, 51.00, 'COMPLETED');
                    """);
        }
    }
}