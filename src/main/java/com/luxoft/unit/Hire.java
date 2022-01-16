package com.luxoft.unit;

public class Hire {
    public String isHired(int age){
        if (age<12)
            return "no";
            else if (age<16)
                return "half";
                else return "yes";
    }
}
