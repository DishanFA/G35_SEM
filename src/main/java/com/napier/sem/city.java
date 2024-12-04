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
        String query = "SELECT *" + " FROM city WHERE district = ? ORDER BY population DESC";

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

    // New method to retrieve the top N populated cities in the world
    public List<city> getTopPopulatedCities(Connection con, int n) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT *" + " FROM city ORDER BY population DESC LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, n);
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

    public List<city> issue8(Connection con) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT *" + "FROM city ORDER BY Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            ;
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

    public List<city> issue9(Connection con, String Continent) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT *" + "FROM city JOIN country ON city.countryCode = country.Code WHERE Continent LIKE ? ORDER BY city.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, Continent);
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

    // method to retrieve capital cities by continent organised by population largest to smallest
    public List<city> getCapitalCitiesByContinent(Connection con, String continent) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? AND country.Capital IS NOT NULL " +
                "ORDER BY city.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                city c = new city();
                c.id = rs.getInt("ID");
                c.name = rs.getString("Name");
                c.countryCode = rs.getString("CountryCode");
                c.district = rs.getString("District");
                c.population = rs.getInt("Population");
                cities.add(c);  // Add the city to the list
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return cities;
    }


    // Method to retrieve the top N populated capital cities in the world
    public List<city> getTopPopulatedCapitalCities(Connection con, int n) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, n);
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



    // method to get top populated capital cities by continent
    public List<city> getTopPopulatedCapitalCitiesByContinent(Connection con, String continent, int n) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population " +
                "FROM city " +
                "JOIN country ON city.CountryCode = country.Code " +
                "WHERE country.Continent = ? AND country.Capital IS NOT NULL " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, continent);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                city c = new city();
                c.id = rs.getInt("ID");
                c.name = rs.getString("Name");
                c.countryCode = rs.getString("CountryCode");
                c.district = rs.getString("District");
                c.population = rs.getInt("Population");
                cities.add(c);  // Add the city to the list
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return cities;
    }

    // get population by district
    public int getPopulationByDistrict(Connection con, String district) {
        int totalPopulation = 0;
        String query = "SELECT SUM(Population) AS total_population " +
                "FROM city WHERE district = ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, district);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                totalPopulation = rs.getInt("total_population");
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }

        return totalPopulation;
    }


    // get top populated capital cities by region
    public List<city> getTopPopulatedCapitalCitiesByRegion(Connection con, String region, int n) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country co ON ci.ID = co.Capital " +
                "WHERE co.Region = ? " +
                "ORDER BY ci.Population DESC " +
                "LIMIT ?";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
            stmt.setInt(2, n);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                city c = new city();
                c.id = rs.getInt("ID");
                c.name = rs.getString("Name");
                c.countryCode = rs.getString("CountryCode");
                c.district = rs.getString("District");
                c.population = rs.getInt("Population");
                cities.add(c); // Add the city to the list
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
        }
        return cities;
    }


    // Method to retrieve all cities in a region ordered by population
    public List<city> getCitiesByRegion(Connection con, String region) {
        List<city> cities = new ArrayList<>();
        String query = "SELECT ci.ID, ci.Name, ci.CountryCode, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country co ON ci.CountryCode = co.Code " +
                "WHERE co.Region = ? " +
                "ORDER BY ci.Population DESC";

        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, region);
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