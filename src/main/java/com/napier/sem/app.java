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
                con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }

            // jdbc:mysql://db:3306/world?useSSL=false

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



        System.out.println("\nEnter the district name to see its total population:");
        String districtName = scanner.nextLine();  // Getting the district name from user

        // fetch the population of the district
        int population = cityObj.getPopulationByDistrict(a.con, districtName);

        // Display the population of the district
        System.out.println("Total population of district " + districtName + ": " + population);





        System.out.println("\nEnter the country code (e.g., 'USA', 'IND') to see its population:");
        String countryCode = scanner.nextLine();  // Getting the country code from user

        // fetch the population
        population = countryObj.getPopulationByCountry(a.con, countryCode);

        // Display the population of the country
        if (population > 0) {
            System.out.println("The population of country " + countryCode + " is: " + population);
        } else {
            System.out.println("Country with code " + countryCode + " not found.");
        }



        // select region to search here
        String regionName = "Southern Europe";

        // Fetch population statistics for the region
        List<country> populationStats = countryObj.getPopulationStatsByRegion(a.con, regionName);

        if (populationStats.isEmpty()) {
            System.out.println("No data found for the specified region: " + regionName);
        } else {
            System.out.println("\nPopulation statistics for region: " + regionName);
            for (country co : populationStats) {
                System.out.println("\nRegion: " + co.region
                        + ", Total Population: " + co.population
                        + ", City Population: " + (long) co.surfaceArea
                        + ", Non-City Population:\n " + co.gnp);
            }
        }


        String region = "Caribbean";

        System.out.println("Enter the number of top populated capital cities to fetch:");
        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N > 0) break; // Ensure the number is positive
                else System.out.println("Please enter a positive number:");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }

        // Fetch the top N populated capital cities in the specified region
        List<city> topCapitalCitiesByRegion = cityObj.getTopPopulatedCapitalCitiesByRegion(a.con, region, N);

        // Display
        if (topCapitalCitiesByRegion.isEmpty()) {
            System.out.println("No data found for the specified region: " + region);
        } else {
            System.out.println("\nTop " + N + " populated capital cities in region: \n" + region);
            for (city c : topCapitalCitiesByRegion) {
                System.out.println(c.name + " - Population: " + c.population);
            }
        }



        region = "Southern and Central Asia";

        List<city> citiesByRegion = cityObj.getCitiesByRegion(a.con, region);

        // Top N populated countries in region where user choses N
        if (citiesByRegion.isEmpty()) {
            System.out.println("No data found for the specified region: " + region);
        } else {
            System.out.println("\nAll cities in the region: " + region + ", organized by population:\n");
            for (city c : citiesByRegion) {
                System.out.println(c.name + " - Population: " + c.population);
            }
        }



    // Fetch the number of people who can speak Arabic
    List<country> arabicSpeakingPopulation = countryObj.getArabicSpeakingPopulation(a.con);

    // Display the results
        if (arabicSpeakingPopulation.isEmpty()) {
        System.out.println("No data found for Arabic-speaking population.");
        } else {
            System.out.println("\nArabic-speaking population organized by greatest number to smallest:\n");
            for (country co : arabicSpeakingPopulation) {
                 System.out.println("Language: " + co.localName +
                    ", Total Speakers: " + co.population +
                    ", Percentage of World Population: " + co.gnp + "%");
        }
    }



// Specify the continent and prompt the user for the value of N

        continent = "Asia";

        System.out.println("Enter the number of top populated countries to fetch in " + continent);

        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N > 0) break;
                else System.out.println("Please enter a positive number:");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number:");
            }
        }


// Fetch the top N populated countries in the specified continent
        List<country> topCountries = countryObj.getTopPopulatedCountriesByContinent(a.con, continent, N);

// Display
        if (topCountries.isEmpty()) {
            System.out.println("No data found for the specified continent: " + continent);
        } else {
            System.out.println("Top " + N + " populated countries in continent: " + continent);
            for (country co : topCountries) {
                System.out.println("Country: " + co.name);
            }
        }

        // Prompt the user for input
        scanner = new Scanner(System.in);

        System.out.println("Enter the continent name:");
        continent = scanner.nextLine();

        System.out.println("Enter the number of top populated cities to retrieve (N):");
        int n = scanner.nextInt();

        // Fetch and display top N cities in the specified continent

        List<city> topCitiesIn = cityObj.getTopNCitiesInContinent(a.con, continent, n);

        System.out.println("Top " + n + " populated cities in " + continent + ":");
        for (city ci : topCitiesIn) {
            System.out.println(ci.name + " - Population: " + ci.population +
                    ", District: " + ci.district +
                    ", Country Code: " + ci.countryCode);
        }

        // Prompt the user for input
        scanner = new Scanner(System.in);

        System.out.println("Enter the region name:");
        region = scanner.nextLine();

        System.out.println("Enter the number of top populated cities to retrieve (N):");
        n = scanner.nextInt();

        // Fetch and display top N cities in the specified region
        cityObj = new city();
        List<city> topCities1 = cityObj.getTopNCitiesInRegion(a.con, region, n);

        System.out.println("Top " + n + " populated cities in " + region + ":");
        for (city ci : topCities1) {
            System.out.println(ci.name + " - Population: " + ci.population +
                    ", District: " + ci.district +
                    ", Country Code: " + ci.countryCode);
        }

        // Prompt the user for input
        scanner = new Scanner(System.in);

        System.out.println("Enter the country name:");
        String countryName = scanner.nextLine();

        System.out.println("Enter the number of top populated cities to retrieve (N):");
        n = scanner.nextInt();

        // Fetch and display top N cities in the specified country
        cityObj = new city();
        List<city> topCities2 = cityObj.getTopNCitiesInCountry(a.con, countryName, n);

        System.out.println("Top " + n + " populated cities in " + countryName + ":");
        for (city ci : topCities2) {
            System.out.println(ci.name + " - Population: " + ci.population +
                    ", District: " + ci.district +
                    ", Country Code: " + ci.countryCode);
        }

        scanner = new Scanner(System.in);

        System.out.println("Enter the district name:");
        districtName = scanner.nextLine();

        System.out.println("Enter the number of top populated cities to retrieve (N):");
        n = scanner.nextInt();

        // Fetch and display top N cities in the specified district
        cityObj = new city();
        List<city> topCities3 = cityObj.getTopNCitiesInDistrict(a.con, districtName, n);

        System.out.println("Top " + n + " populated cities in " + districtName + ":");
        for (city ci : topCities3) {
            System.out.println(ci.name + " - Population: " + ci.population +
                    ", District: " + ci.district +
                    ", Country Code: " + ci.countryCode);
        }

        // Fetch and display all capital cities by population
        cityObj = new city();
        List<city> capitalCities1 = cityObj.getAllCapitalCitiesByPopulation(a.con);

        System.out.println("All the capital cities in the world, organized by largest population to smallest:");
        for (city ci : capitalCities1) {
            System.out.println(ci.name + " (Country: " + ci.countryCode + ") - Population: " + ci.population);
        }

        // Disconnect from database
        a.disconnect();
    }
}
