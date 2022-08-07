package com.checkr.interviews;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<String, String> options = new HashMap<>();
            options.put("company_name", "Facebook");
            options.put("round", "a");
            System.out.println(FundingRaised.where(options).size());
            System.out.println(FundingRaised.findBy(options).size());
        } catch(Exception e) {
            System.out.print(e.getMessage());
            System.out.print("error");
        }
    }
}
