package com.napier.sem;

import javax.naming.Name;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class country {
    // Fields corresponding to table columns
    public String code; // Primary key
    public String name;
    public String continent; // Stored as ENUM in the database
    public String region;
    public double surfaceArea; // Decimal(10,2)
    public Integer indepYear; // Can be NULL
    public int population;
    public Double lifeExpectancy; // Decimal(3,1), Can be NULL
    public Double gnp; // Decimal(10,2), Can be NULL
    public Double gnpOld; // Decimal(10,2), Can be NULL
    public String localName;
    public String governmentForm;
    public String headOfState; // Can be NULL
    public Integer capital; // Can be NULL
    public String code2;

    //Method to organise countries in the world by population
    public List<country> issue2(Connection con) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT *" + "FROM country ORDER BY Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                country co = new country();
                co.code = rs.getString("Code");
                co.name = rs.getString("Name");
                co.continent = rs.getString("Continent");
                co.region = rs.getString("Region");
                co.population = rs.getInt("Population");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    public List<country> issue3(Connection con, String continent) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT *" + "FROM country WHERE Continent LIKE ? ORDER BY Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.code = rs.getString("Code");
                co.name = rs.getString("Name");
                co.continent = rs.getString("Continent");
                co.region = rs.getString("Region");
                co.population = rs.getInt("Population");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    public List<country> issue4(Connection con, String region) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT *" + "FROM country WHERE Region LIKE ? ORDER BY Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.code = rs.getString("Code");
                co.name = rs.getString("Name");
                co.continent = rs.getString("Continent");
                co.region = rs.getString("Region");
                co.population = rs.getInt("Population");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    //Long variable for world population
    public long worldPopulation;

    public List<country> issue27(Connection con) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT SUM(Population) AS WorldPopulation " + "FROM country";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.worldPopulation = rs.getLong("WorldPopulation");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    public List<country> issue28(Connection con, String continent) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT Continent, SUM(Population) AS ContinentPopulation " + "FROM country WHERE Continent LIKE ? GROUP BY Continent";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.continent = rs.getString("Continent");
                co.population = rs.getInt("ContinentPopulation");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    public List<country> issue29(Connection con, String region) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT Region, SUM(Population) AS RegionPopulation " + "FROM country WHERE Region LIKE ? GROUP BY Region";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.region = rs.getString("Region");
                co.population = rs.getInt("RegionPopulation");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }


    public int getPopulationByCountry(Connection con, String countryCode) {
        int population = 0;
        String query = "SELECT Population FROM country WHERE Code = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, countryCode);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                population = rs.getInt("Population");
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return population;
    }


    public List<country> getPopulationStatsByRegion(Connection con, String region) {
        List<country> stats = new ArrayList<>();
        String query = "SELECT co.Region, "
                + "SUM(c.Population) AS CityPopulation, "
                + "(SELECT SUM(co.Population) FROM country co WHERE co.Region = ?) AS TotalPopulation, "
                + "((SELECT SUM(co.Population) FROM country co WHERE co.Region = ?) - SUM(c.Population)) AS NonCityPopulation "
                + "FROM country co "
                + "LEFT JOIN city c ON co.Code = c.CountryCode "
                + "WHERE co.Region = ? "
                + "GROUP BY co.Region";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
            stmt.setString(2, region);
            stmt.setString(3, region);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                country co = new country();
                co.region = rs.getString("Region");
                co.population = rs.getInt("TotalPopulation");
                co.surfaceArea = rs.getLong("CityPopulation"); // Using `surfaceArea` temporarily for city population
                co.gnp = rs.getDouble("NonCityPopulation"); // Using `gnp` temporarily for non-city population
                stats.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return stats;
    }







    /////// from here





    // Method to calculate the number of people who can speak Arabic
    public List<country> getArabicSpeakingPopulation(Connection con) {
        List<country> results = new ArrayList<>();
        String query = "SELECT cl.Language, " +
                "SUM(co.Population * cl.Percentage / 100) AS TotalSpeakers, " +
                "(SUM(co.Population * cl.Percentage / 100) / " +
                "(SELECT SUM(Population) FROM country) * 100) AS WorldPercentage " +
                "FROM country co " +
                "JOIN countrylanguage cl ON co.Code = cl.CountryCode " +
                "WHERE cl.Language = 'Arabic' " +
                "GROUP BY cl.Language " +
                "ORDER BY TotalSpeakers DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                country co = new country();
                co.localName = rs.getString("Language");
                co.population = (int) rs.getLong("TotalSpeakers");
                co.gnp = rs.getDouble("WorldPercentage");
                results.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return results;
    }


    // Method to retrieve the top N populated countries in a continent
    public List<country> getTopPopulatedCountriesByContinent(Connection con, String continent, int n) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT Code, Name, Population, Continent, Region " +
                "FROM country " +
                "WHERE Continent = ? " +
                "ORDER BY Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                country co = new country();
                co.code = rs.getString("Code");
                co.name = rs.getString("Name");
                co.continent = rs.getString("Continent");
                co.region = rs.getString("Region");
                co.population = rs.getInt("Population");
                countries.add(co);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }




}



