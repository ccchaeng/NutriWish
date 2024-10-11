// Supplement.java
package com.example.nutriwish;

import java.io.Serializable;

public class Supplement implements Serializable {
    private String name;
    private String benefits;
    private String usage;
    private String precautions;

    public Supplement(String name, String benefits, String usage, String precautions) {
        this.name = name;
        this.benefits = benefits;
        this.usage = usage;
        this.precautions = precautions;
    }

    public String getName() {
        return name;
    }

    public String getBenefits() {
        return benefits;
    }

    public String getUsage() {
        return usage;
    }

    public String getPrecautions() {
        return precautions;
    }
}
