package com.napier.sem;

public class countryLanguage {
    // Fields corresponding to table columns
    private String countryCode;  // Primary Key (part 1)
    private String language;     // Primary Key (part 2)
    private boolean isOfficial;  // Stored as 'T' or 'F' in the table
    private double percentage;   // Percentage with precision 4,1
}
