package com.unimelb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import com.opencsv.*;
import com.opencsv.exceptions.CsvValidationException;
import com.opencsv.validators.RowFunctionValidator;
import com.opencsv.validators.RowValidator;

public class dictionary {
    private Map<String, String> dictionaryData;
    private String dictionaryFile;

    public dictionary(String dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
        this.dictionaryData = new ConcurrentHashMap<>(); //future proofing
    }

    public String addNewEntry(String newWord, String definitions){
        if (dictionaryData.containsKey(newWord)){
            return "Already here";
        } else {
            dictionaryData.put(newWord, definitions);
            return "success";
        }
    }

    public String removeEntry(String word){
        if (!dictionaryData.containsKey(word)){
            return "not here";
        } else {
            dictionaryData.remove(word);
            return "success";
        }
    }

    public String retrieveEntry(String word){
        if (!dictionaryData.containsKey(word)){
            return "not found";
        } else {
            return dictionaryData.get(word);
        }
    }

    public void initializeDictionary(){
        try {
            CSVReader readIn = new CSVReaderBuilder(new FileReader(dictionaryFile)).withRowValidator(TWO_COLUMN_ROW_VALIDATOR).build();
            try {
                String [] nextLine;
                while((nextLine = readIn.readNext()) != null) {
                    System.out.println("New Dictionary line: " + nextLine[0] + " " + nextLine[1]);
                    dictionaryData.put(nextLine[0], nextLine[1]);
                }
            } catch (IOException e) {
                System.out.println("IOEXCEPTION");
                e.printStackTrace();
            } catch (CsvValidationException e) {
                System.out.println("CSV Validation Exception");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            System.out.println("File Not Found Exception");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("other exception");
            e.printStackTrace();
        }

    }

    public static final Function<String[], Boolean> TWO_COLUMN_ROW = (x) -> x.length ==2;
    private static final RowValidator TWO_COLUMN_ROW_VALIDATOR = new RowFunctionValidator(TWO_COLUMN_ROW, "Row must have two columns");

}
