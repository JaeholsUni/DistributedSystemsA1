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

import static com.unimelb.arrayUtils.*;

public class dictionary {
    private static final String WORD_ALREADY_PRESENT = "Word already present in dictionary";
    private static final String WORD_ADDED_CORRECTLY = "Word has been added to dictionary";
    private static final String WORD_NOT_FOUND = "Word not found in dictionary";
    private static final String WORD_REMOVED_CORRECTLY = "Word as been removed from dictionary";
    private static final String WORD_UPDATED_CORRECTLY = "Word has been updated correctly";

    private Map<String, String> dictionaryData;
    private String dictionaryFile;

    public dictionary(String dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
        this.dictionaryData = new ConcurrentHashMap<>(); //future proofing
    }

    public String[] addNewEntry(String newWord, String definitions){
        if (dictionaryData.containsKey(newWord)){
            return tupleMaker(FAILURE_MESSAGE, WORD_ALREADY_PRESENT);
        } else {
            dictionaryData.put(newWord, definitions);
            return tupleMaker(SUCCESS_MESSAGE, WORD_ADDED_CORRECTLY);
        }
    }

    public String[] removeEntry(String word){
        if (!dictionaryData.containsKey(word)){
            return tupleMaker(FAILURE_MESSAGE, WORD_NOT_FOUND);
        } else {
            dictionaryData.remove(word);
            return tupleMaker(SUCCESS_MESSAGE, WORD_REMOVED_CORRECTLY);
        }
    }

    public String[] retrieveEntry(String word){
        if (!dictionaryData.containsKey(word)){
            return tupleMaker(FAILURE_MESSAGE, WORD_NOT_FOUND);
        } else {
            return tupleMaker(SUCCESS_MESSAGE, dictionaryData.get(word));
        }
    }

    public String[] updateEntry(String word, String definitions) {
        if (dictionaryData.containsKey(word)){
            dictionaryData.remove(word);
            dictionaryData.put(word, definitions);
            return tupleMaker(SUCCESS_MESSAGE, WORD_UPDATED_CORRECTLY);
        } else {
            return tupleMaker(FAILURE_MESSAGE, WORD_NOT_FOUND);
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
