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
            ;
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
}



