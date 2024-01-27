package me.morishima.h0llution.api.worlddata;

public interface IPollution {

    double getPollution();

    void setPollution(double var);
    void addPollution(double var);

    void removePollution();
    void minimizePollution(double var);


}
