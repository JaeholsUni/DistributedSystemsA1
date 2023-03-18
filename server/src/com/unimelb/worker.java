package com.unimelb;
//A bit more future proofing

public class worker {

    public String sortTask(dictionary dictionary, String[] contents){
        switch (contents[0]) {
            case "get":
                return (dictionary.retrieveEntry(contents[1]));
            case "delete":
                return dictionary.removeEntry(contents[1]);
            case "add":
                return dictionary.addNewEntry(contents[1], contents[2]);
            default:
                return "Failure";
        }
    }
}
