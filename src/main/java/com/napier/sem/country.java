package com.napier.sem;

import java.sql.*;


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
}

