package com.napier.sem;

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

    public List<country> issue2(Connection con) {
        List<country> countries = new ArrayList<>();

        String query = "SELECT Code, Name, Continent, Region, Population FROM country ORDER BY Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();

            // Loop through the result set and populate the country objects
            while (rs.next()) {
                country co = new country();
                co.code = rs.getString("Code");
                co.name = rs.getString("Name");  // Ensure column names match the database
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

    // fetch all countries in a continent
    public List<country> issue3(Connection con, String continent) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT Name, Population, Continent" + " FROM country WHERE Continent = ? ORDER BY population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                country co = new country();
                co.name = rs.getString("Name");
                co.continent = rs.getString("Continent");
                co.population = rs.getInt("Population");
                countries.add(co);
            }

        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return countries;
    }

    // fetch all countries in a continent
    public List<country> issue4(Connection con, String region) {
        List<country> countries = new ArrayList<>();
        String query = "SELECT Name, Population, Region" + " FROM country WHERE Region = ? ORDER BY population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                country co = new country();
                co.name = rs.getString("Name");
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



