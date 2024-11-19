package com.napier.sem;

import java.awt.desktop.SystemSleepListener;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

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
                Thread.sleep(300);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
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


    public static void main(String[] args)
    {
        // Create new Application
        app a = new app();

        // Connect to database
        a.connect();

        // Create an instance of city to access its methods
        city cityObj = new city();

        // Fetch all cities in the country code
        List<city> cities = cityObj.getCitiesByCountry(a.con, "AFG");

        // Display the cities
        for (city c : cities) {
            System.out.println(c.name + " - Population: " + c.population);
        }
        // Fetch all cities in the district
        List<city> citiesByDistrict = cityObj.getCitiesByDistrict(a.con, "Kabol");

        // Display the cities in the district ordered by population
        System.out.println("\nCities ordered by population:");
        for (city c : citiesByDistrict) {
            System.out.println(c.name + " - Population: " + c.population);
        }

        // Fetch the top N populated cities in the world
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the number of top city's you want to see:");
        int N; // Example: Get the top N
        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N > 0) break; // Ensure the number is positive
                else System.out.println("Please enter a positive number:");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }

        List<city> topCities = cityObj.getTopPopulatedCities(a.con, N);

        // Display the top N populated cities
        System.out.println("Top " + N + " populated cities in the world:");
        for (city c : topCities) {
            System.out.println(c.name + " - Population: " + c.population);
        }

        // Disconnect from database
        a.disconnect();
    }
}
