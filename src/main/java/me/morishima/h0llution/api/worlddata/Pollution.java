package me.morishima.h0llution.api.worlddata;

public class Pollution implements IPollution {

    private double pollution;

    @Override
    public double getPollution() {
        return pollution;
    }

    @Override
    public void setPollution(double var) {
        this.pollution = var;
    }

    @Override
    public void addPollution(double var) {
        this.pollution = pollution + var;
    }

    @Override
    public void removePollution() {
        this.pollution = 0;
    }

    @Override
    public void minimizePollution(double var) {
        this.pollution = pollution - var;
    }
}
