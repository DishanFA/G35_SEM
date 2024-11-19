package com.napier.sem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class city {
    public int id;               // Maps to `ID` in the table
    public String name;          // Maps to `Name` in the table
    public String countryCode;   // Maps to `CountryCode` in the table
    public String district;      // Maps to `District` in the table
    public int population;       // Maps to `Population` in the table

    // Method to retrieve all cities in a country ordered by population
    public List<city> getCitiesByCountry(Connection con, String countryCode) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT * " + "FROM city WHERE countryCode = ? ORDER BY population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, countryCode);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                city c = new city();
                c.id = rs.getInt("ID");
                c.name = rs.getString("Name");
                c.countryCode = rs.getString("CountryCode");
                c.district = rs.getString("District");
                c.population = rs.getInt("Population");
                cities.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return cities;
    }

    // New method to retrieve all cities in a district ordered by population
    public List<city> getCitiesByDistrict(Connection con, String district) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT * FROM city WHERE district = ? ORDER BY population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, district);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                city c = new city();
                c.id = rs.getInt("ID");
                c.name = rs.getString("Name");
                c.countryCode = rs.getString("CountryCode");
                c.district = rs.getString("District");
                c.population = rs.getInt("Population");
                cities.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return cities;
    }
}
