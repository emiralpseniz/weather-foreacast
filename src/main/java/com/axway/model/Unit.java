package com.axway.model;

import java.util.Arrays;

/**
 * Created by emiralpseniz on 20/08/2017.
 *
 * Various temperature units
 */
public enum Unit {
    KELVIN(""),
    FAHRENHEIT("imperial"),
    CELSIUS("metric");

    private String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }

    public static Unit valueFromString(String stringUnit) {
        return Arrays.stream(Unit.values())
                .filter(unit -> unit.toString().equals(stringUnit))
                .findFirst()
                .orElse(KELVIN);
    }
}
