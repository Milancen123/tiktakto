package com.example.demo2;

import java.sql.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Connect {


    public void initDB(String prvi, String drugi, int pobednik)
    {
        String url = "jdbc:sqlite:db/db.db";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement statement = conn.prepareStatement(
                     "INSERT INTO Mec (prviIgrac, drugiIgrac, Pobednik) VALUES (?, ?, ?)")) {

            // Set parameters for the prepared statement
            statement.setString(1, prvi);
            statement.setString(2, drugi);
            statement.setInt(3, pobednik);

            // Execute the statement
            statement.executeUpdate();


        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
