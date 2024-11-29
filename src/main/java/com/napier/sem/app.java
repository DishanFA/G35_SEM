package com.napier.sem;

import javax.naming.Name;
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
    public Connection con = null;

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
                Thread.sleep(3000);
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


    public static void main(String[] args)
    {
        // Create new Application
        app a = new app();

        // Connect to database
        a.connect();

        // Create an instance of city to access its methods
        city cityObj = new city();

        // Create an instance of country to access its methods
        country countryObj = new country();

        //make N have a value of 1 to start
        int N = 1;


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

        List<city> citiesByWorld = cityObj.issue8(a.con);

        System.out.println("cities in the world ordered by population (largest to smallest)");
        for (city c : citiesByWorld) {
            System.out.println(c.name + " - Population: " + c.population);
        }

        List<city> citiesByContinent = cityObj.issue9(a.con,"Europe");

        System.out.println("cities in the continent ordered by population (largest to smallest)");
        for (city c : citiesByContinent) {
            System.out.println(c.name + " - Population: " + c.population);
        }


        // Fetch the top N populated cities in the world
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the number of top city's you want to see:");
        // Example: Get the top N
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



        List<country> countries = countryObj.issue2(a.con);

        //display all countries in the world
        System.out.println("Countries in the world ordered by population (largest to smallest):");
        for (country co : countries) {
            System.out.println(co.name + " - Population: " + co.population);
        }

        //fetching all countries in the continent
        List<country> countriesByContinent = countryObj.issue3(a.con,"Asia" );

        //display all countries in the continent
        System.out.println("All the countries in the district by population largest to smallest");
        for (country co : countriesByContinent) {
            System.out.println(co.name + " - Population: " + co.population + " - Continent: " + co.continent);
        }

        //fetching all countries in the region
        List<country> countriesByRegion = countryObj.issue4(a.con, "Caribbean");

        //display all countries in the region
        System.out.println("All the countries in the region by population largest to smallest");
        for (country co : countriesByRegion) {
            System.out.println(co.name + " - Population: " + co.population + " - Region: " + co.region);
        }

        //fetching total population of the world
        List<country> worldPopulation = countryObj.issue27(a.con);

        //display total population of the world
        System.out.println("The population of the world");
        for (country co : worldPopulation) {
            System.out.println("World Population: " + co.worldPopulation);
        }


        //fetching total population of a continent
        List<country> continentPopulation = countryObj.issue28(a.con, "Europe");

        //display total population of a continent
        System.out.println("The population of a continent");
        for (country co : continentPopulation) {
            System.out.println("Continent: " + co.continent + " - Population: " + co.population);
        }

        //fetching total population of a region
        List<country> regionPopulation = countryObj.issue29(a.con, "Caribbean");

        //display total population of a region
        System.out.println("The population of a region");
        for (country co : regionPopulation) {
            System.out.println("Region: " + co.region + " - Population: " + co.population);
        }


        //display capital cities by continent, ordered by pop largest - smallest
        String continent = "Europe";
        List<city> capitalCities = cityObj.getCapitalCitiesByContinent(a.con, continent);

        System.out.println("\nCapital Cities in " + continent + " ordered by population (largest to smallest):\n");
        for (city c : capitalCities) {
            System.out.println(c.name + " - Population: " + c.population);
        }




        //display the top populated capital cites amount chosen by user
        System.out.println("\nEnter the number of top populated capital cities you want to see:");
        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N > 0) break;
                else System.out.println("Please enter a positive number:");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }



        List<city> topCapitalCities = cityObj.getTopPopulatedCapitalCities(a.con, N);

        // Display the top N populated capital cities
        System.out.println("\nTop " + N + " populated capital cities in the world:");
        for (city c : topCapitalCities) {
            System.out.println(c.name + " - Population: " + c.population);
        }



        //display the top populated capital cites amount chosen by user
        System.out.println("\nEnter the number of top populated capital cities you want to see within the continent:");
        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N > 0) break;
                else System.out.println("Please enter a positive number:");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }

        //fetch the users input for continent
        System.out.println("\nEnter the continent you want to see the top populated capital cities of:");
        continent = scanner.nextLine();

        // Fetch the top N populated capital cities by continent
        topCapitalCities = cityObj.getTopPopulatedCapitalCitiesByContinent(a.con, continent, N);
        System.out.println("\nThe top " + N + " populated capital cities in " + continent + ":");
        for (city c : topCapitalCities) {
            System.out.println(c.name + " - Population: " + c.population);
        }






        // Disconnect from database
        a.disconnect();
    }
}
