package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static app app;

    @BeforeAll
    static void init()
    {
        app = new app();
        app.connect();

    }

    @Test
    void testGetcity()
    {

    }
}