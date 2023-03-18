package com.unimelb;
//A bit more future proofing

public class worker {

    public String sortTask(dictionary dictionary, String[] contents){
        if (!contentValidator(contents)) {
            return "Failure";
        }

        switch (contents[0]) {
            case "get":
                return (dictionary.retrieveEntry(contents[1]));
            case "delete":
                return dictionary.removeEntry(contents[1]);
            case "add":
                return dictionary.addNewEntry(contents[1], contents[2]);
            case "update":
                return dictionary.updateEntry(contents[1], contents[2]);
            default:
                return "Failure";
        }
    }

    private boolean contentValidator(String[] contents) {
        if (contents[0] == null || contents[1] == null){
            System.out.println("1 or 2 null");
            return false;
        } else {
            if ((contents[0].equals("add") || contents[0].equals("update")) && contents[2] == null) {

                System.out.println("3 null and required");
                return false;
            }

            System.out.println("all g");
            return true;
        }
    }
}
