package com.napier.sem;

public class country {
    // Fields corresponding to table columns
    private String code; // Primary key
    private String name;
    private String continent; // Stored as ENUM in the database
    private String region;
    private double surfaceArea; // Decimal(10,2)
    private Integer indepYear; // Can be NULL
    private int population;
    private Double lifeExpectancy; // Decimal(3,1), Can be NULL
    private Double gnp; // Decimal(10,2), Can be NULL
    private Double gnpOld; // Decimal(10,2), Can be NULL
    private String localName;
    private String governmentForm;
    private String headOfState; // Can be NULL
    private Integer capital; // Can be NULL
    private String code2;
}
