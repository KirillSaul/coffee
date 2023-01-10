package com.example.coffee.entity.schedule;

public enum DaysOfTheWeek {
    MONDAY("пн"),
    TUESDAY("вт"),
    WEDNESDAY("ср"),
    THURSDAY("чт"),
    FRIDAY("пт"),
    SATURDAY("сб"),
    SUNDAY("вс");

    private final String value;

    DaysOfTheWeek(String value) {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
