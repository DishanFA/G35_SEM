package com.napier.sem;

public class countryLanguage {
    // Fields corresponding to table columns
    public String countryCode;  // Primary Key (part 1)
    public String language;     // Primary Key (part 2)
    public boolean isOfficial;  // Stored as 'T' or 'F' in the table
    public double percentage;   // Percentage with precision 4,1
}
