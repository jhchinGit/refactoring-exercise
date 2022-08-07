package com.checkr.interviews;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FundingRaisedCSVReader {

    public static List<String[]> getFundingRaisedCSV() throws IOException {
        List<String[]> csvData = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"))) {
            String[] row;

            while((row = reader.readNext()) != null)
                csvData.add(row);

            csvData.remove(0);
        }

        return csvData;
    }
}

