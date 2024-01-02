package com.example.Foods.riotApi.entity;


public enum SpellType {
//    VALUE_1(21, "베리어"),
//    VALUE_2(1, "정화"),
//    VALUE_3(14, "점화"),
//    VALUE_4(3, "탈진"),
//    VALUE_5(4, "탈진"),
//    VALUE_6(6, "유체화"),
//    VALUE_7(7, "회복"),
//    VALUE_8(13, "총명"),
//    VALUE_9(30, "왕을 향해!"),
//    VALUE_10(31, "포로 던지기"),
//    VALUE_11(11, "강타"),
//    VALUE_12(39, "표식"),
//    VALUE_13(32, "표식"),
//    VALUE_14(12, "순간이동"),
//    VALUE_15(54, "게임 시작 후 결정"),
//    VALUE_16(55, "TBD 및 공격-강타");
    Null(0),
    베리어(21),
    정화(1),
    점화(14),
    탈진(3),
    점멸(4),
    유체화(6),
    회복(7),
    총명(13),
    왕을향해(30),
    포로던지기(31),
    강타(11),
    표식우르프(39),
    표식(32),
    순간이동(12),
    게임시작후결정(54),
    공격강타(55);



    private int numericValue;

    SpellType(int numericValue) {
        this.numericValue = numericValue;

    }

    public int getNumericValue() {
        return numericValue;
    }
    public static SpellType fromNumericValue(int numericValue) {
        for (SpellType value : SpellType.values()) {
            if (value.numericValue == numericValue) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid numeric value: " + numericValue);
    }
}
