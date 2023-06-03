package com.codecool.marsexploration.data;

import java.util.Objects;

public final class SimulationInput {
    private String mapPath;
    private final Coordinate landing;
    private final long timeout;
    private final String logPath;

    public SimulationInput(String mapPath, Coordinate landing, long timeout, String logPath) {
        this.mapPath = mapPath;
        this.landing = landing;
        this.timeout = timeout;
        this.logPath = logPath;
    }

    public SimulationInput(Coordinate landing, long timeout, String logPath) {
        this.landing = landing;
        this.timeout = timeout;
        this.logPath = logPath;
    }

    public Coordinate landing() {
        return landing;
    }

    public long timeout() {
        return timeout;
    }

    public String logPath() {
        return logPath;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (SimulationInput) obj;
        return Objects.equals(this.mapPath, that.mapPath) &&
                Objects.equals(this.landing, that.landing) &&
                this.timeout == that.timeout &&
                Objects.equals(this.logPath, that.logPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapPath, landing, timeout, logPath);
    }

    @Override
    public String toString() {
        return "SimulationInput[" +
                "mapPath=" + mapPath + ", " +
                "landing=" + landing + ", " +
                "timeout=" + timeout + ", " +
                "logPath=" + logPath + ']';
    }
}
