package org.sapient.tbs.util;

public enum DiscountCriteria {
    AFTERNOON_TIME("12-17"), TICKETS("3");

    private String data;

    DiscountCriteria(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
