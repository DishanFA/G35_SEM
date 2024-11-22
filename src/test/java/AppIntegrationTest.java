import com.napier.sem.app;
import com.napier.sem.city;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AppIntegrationTest
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

        List<city> cities = cityObj.getCitiesByCountry(app.con, "AFG");

        for (city c : cities) {
            System.out.println(c.name + " - Population: " + c.population);
        }
    }
}