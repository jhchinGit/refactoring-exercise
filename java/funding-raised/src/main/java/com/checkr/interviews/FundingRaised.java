package com.checkr.interviews;

import java.util.*;
import java.io.IOException;

public class FundingRaised {
    private static final int COMPANY_NAME_INDEX = 1;
    private static final int CITY_INDEX = 4;
    private static final int STATE_INDEX = 5;
    private static final int ROUND_INDEX = 9;
    private static final String COMPANY_NAME_KEY = "company_name";
    private static final String PERMALINK_KEY = "permalink";
    private static final String NUMBER_EMPLOYEES_KEY = "number_employees";
    private static final String CATEGORY_KEY = "category";
    private static final String CITY_KEY = "city";
    private static final String STATE_KEY = "state";
    private static final String FUNDED_DATE_KEY = "funded_date";
    private static final String RAISED_AMOUNT_KEY = "raised_amount";
    private static final String RAISED_CURRENCY_KEY = "raised_currency";
    private static final String ROUND_KEY = "round";
    private static final Map<String, Integer> filterMap = Map.of(
            COMPANY_NAME_KEY, COMPANY_NAME_INDEX,
            CITY_KEY, CITY_INDEX,
            STATE_KEY, STATE_INDEX,
            ROUND_KEY, ROUND_INDEX
    );

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> fundingRaisedCSV = FundingRaisedCSVReader.getFundingRaisedCSV();
        List<String[]> filterCSVList = filterCSVByOptions(options, fundingRaisedCSV);

        return generateOutput(filterCSVList);
    }

    private static List<String[]> filterCSVByOptions(Map<String, String> options, List<String[]> fundingRaisedCSV) {
        for(Map.Entry<String, Integer> entry : filterMap.entrySet())
            fundingRaisedCSV = filter(options.get(entry.getKey()), fundingRaisedCSV, entry.getValue());

        return fundingRaisedCSV;
    }

    private static List<Map<String, String>> generateOutput(List<String[]> filterCSVList) {
        List<Map<String, String>> output = new ArrayList<>();

        for (String[] csvArray: filterCSVList)
            output.add(mapCSVData(csvArray));

        return output;
    }

    private static List<String[]> filter(String optionValue, List<String[]> csvData, int csvValueIndex) {
        if(optionValue == null) {
            return csvData;
        }

        List<String[]> results = new ArrayList<>();

        for(String[] csvArray : csvData)
            if(csvArray[csvValueIndex].equals(optionValue))
                results.add(csvArray);

        return results;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = FundingRaisedCSVReader.getFundingRaisedCSV();
        Map<String, String> mapped = new HashMap<>();

        for(String[] csvArr : csvData) {
            mapped = extractCSVMap(options, mapped, csvArr);

            if (mapped != null)
                return mapped;
        }

        throw new NoSuchEntryException();
    }

    private static Map<String, String> extractCSVMap(Map<String, String> options, Map<String, String> mapped, String[] csvArr) {
        for(Map.Entry<String, Integer> entry : filterMap.entrySet()){
            String optionValue = options.get(entry.getKey());
            mapped = extractCSVArray(optionValue, csvArr, entry.getValue(), mapped);
            if (mapped == null)
                return null;
        }

        return mapped;
    }

    private static Map<String, String> extractCSVArray(String optionValue, String[] csvArr, int csvValueIndex, Map<String, String> mapped) {
        if(optionValue == null) {
            return mapped;
        }
        else if(csvArr[csvValueIndex].equals(optionValue)) {
            return mapCSVData(csvArr);
        } else {
            return null;
        }
    }

    private static Map<String, String> mapCSVData(String[] csvArr) {
        Map<String, String> mapped = new HashMap<>();

        mapped.put(PERMALINK_KEY, csvArr[0]);
        mapped.put(COMPANY_NAME_KEY, csvArr[1]);
        mapped.put(NUMBER_EMPLOYEES_KEY, csvArr[2]);
        mapped.put(CATEGORY_KEY, csvArr[3]);
        mapped.put(CITY_KEY, csvArr[4]);
        mapped.put(STATE_KEY, csvArr[5]);
        mapped.put(FUNDED_DATE_KEY, csvArr[6]);
        mapped.put(RAISED_AMOUNT_KEY, csvArr[7]);
        mapped.put(RAISED_CURRENCY_KEY, csvArr[8]);
        mapped.put(ROUND_KEY, csvArr[9]);

        return mapped;
    }
}