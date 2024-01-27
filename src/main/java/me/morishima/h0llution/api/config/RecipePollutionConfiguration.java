package me.morishima.h0llution.api.config;

public class RecipePollutionConfiguration {

    public final String recipeMapName;
    public final double pollution;

    public RecipePollutionConfiguration(String recipeMapName, double pollution) {
        this.recipeMapName = recipeMapName;
        this.pollution = pollution;
    }
}
