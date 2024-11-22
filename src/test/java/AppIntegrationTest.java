import com.napier.sem.app;
import com.napier.sem.city;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Scanner;

  /* public class AppIntegrationTest
{
    static com.napier.sem.app app;

    @BeforeAll
    static void init()
    {
        app = new app();
        app.connect();

    }

    @Test

    public void testGetCitiesByCountry()
    {
        city cityObj = new city();

        // Fetch all cities in the country code
        List<city> cities = cityObj.getCitiesByCountry(app.con, "AFG");

        // Display the cities
        for (city c : cities) {
            System.out.println(c.name + " - Population: " + c.population);
        }
        // Fetch all cities in the district
        List<city> citiesByDistrict = cityObj.getCitiesByDistrict(app.con, "Kabol");

        // Display the cities in the district ordered by population
        System.out.println("\nCities ordered by population:");
        for (city c : citiesByDistrict) {
            System.out.println(c.name + " - Population: " + c.population);
        }

        // Fetch the top N populated cities in the world
        int n1 = -1;
        int n2 = 0;
        int n3 = 4;
        List<city> topCities = cityObj.getTopPopulatedCities(app.con, n1);

        // Display the top N populated cities
        System.out.println("Top " + n1 + " populated cities in the world:");
        for (city c : topCities) {
            System.out.println(c.name + " - Population: " + c.population);
        }
        List<city> topCities2 = cityObj.getTopPopulatedCities(app.con, n2);

        // Display the top N populated cities
        System.out.println("Top " + n2 + " populated cities in the world:");
        for (city c : topCities2) {
            System.out.println(c.name + " - Population: " + c.population);
        }
        List<city> topCities3 = cityObj.getTopPopulatedCities(app.con, n3);

        // Display the top N populated cities
        System.out.println("Top " + n3 + " populated cities in the world:");
        for (city c : topCities3) {
            System.out.println(c.name + " - Population: " + c.population);
        }
    }
}
 */