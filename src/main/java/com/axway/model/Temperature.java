package com.axway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by emiralpseniz on 20/08/2017.
 *
 * Lowest and highest temperature for a given date.
 */
public class Temperature implements Serializable, Comparable<Temperature> {

    private static final long serialVersionUID = -8717359740802256113L;

    private String date;

    private Double lowest;

    private Double highest;

    public Temperature() {
    }

    public Temperature(String date, Double lowest, Double highest) {
        this.date = date;
        this.lowest = lowest;
        this.highest = highest;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getLowest() {
        return lowest;
    }

    public void setLowest(Double lowest) {
        this.lowest = lowest;
    }

    public Double getHighest() {
        return highest;
    }

    public void setHighest(Double highest) {
        this.highest = highest;
    }

    @Override
    public int compareTo(Temperature o) {
        return date.compareTo(o.getDate());
    }
}
