package com.eltonb.fp.model;

public enum Gender {
    MALE("M"), FEMALE("F");

    private final String code;
    Gender(String code) {
        this.code = code;
    }

    public static Gender fromCode(String code) {
        if ("F".equals(code))
            return FEMALE;
        if ("M".equals(code))
            return MALE;
        throw new IllegalArgumentException("invalid gender code: " + code);
    }
}
