package com.napier.sem;

import java.sql.*;

public class app
{
    /**
     *The main method is the entry point of the application.
     * it connects to the database
     * @param args Command-line arguments passed to the program.
     * If no arguments are provided, the array will be empty.
     */
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Search and display all cities in a given country using an existing database connection.
     * @param countryName The name of the country to search for cities.
     */
    public void searchCitiesByCountry(String countryName) {
        // SQL query to get all cities in a specified country
        String query =
                "SELECT city.Name AS CityName, city.District, city.Population"
                +"FROM city"
                +"JOIN country ON city.CountryCode = country.Code"
                +"WHERE country.Name = ?"
                +"ORDER BY city.Population DESC";


        try {
            // Prepare the statement using the provided connection
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, countryName); // Set the country name dynamically

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Display the results
            System.out.printf("Cities in %s:%n", countryName);
            while (resultSet.next()) {
                String cityName = resultSet.getString("CityName");
                String district = resultSet.getString("District");
                int population = resultSet.getInt("Population");

                // Output city details
                System.out.printf("City: %s, District: %s, Population: %d%n", cityName, district, population);
            }

            // Closing resources
            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        // Create new Application
        app a = new app();

        // Connect to database
        a.connect();

        // Perform the search for cities in a given country (example: "India")
        a.searchCitiesByCountry("India");

        // Disconnect from database
        a.disconnect();
    }
}
