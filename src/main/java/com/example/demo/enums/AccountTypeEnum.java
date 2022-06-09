package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountTypeEnum {

    SAVINGS("savings"),

    CURRENT("current");

    private String value;

    AccountTypeEnum(String value)
    {
        this.value = value;
    }

    @JsonCreator
    public static AccountTypeEnum fromValue(String text)
    {
        for (AccountTypeEnum b : AccountTypeEnum.values())
        {
            if (String.valueOf(b.value).equals(text))
            {
                return b;
            }
        }
        return null;
    }

    public String getValue(){
        return this.value;
    }

    @Override
    @JsonValue
    public String toString()
    {
        return String.valueOf(value);
    }

}
