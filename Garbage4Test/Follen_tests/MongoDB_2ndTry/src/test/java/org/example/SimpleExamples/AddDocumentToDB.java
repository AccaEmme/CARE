package org.example.SimpleExamples;

import com.ranauro.manager.MongoManager;
import org.junit.Test;

import org.bson.Document;

public class AddDocumentToDB {
    @Test
    public void addDocument(){
        Document document = new Document();
            document.append("Name" , "Giuliano");
            document.append("Surname" , "Ranauro");
            document.append("Age" , "21");

        MongoManager mongoManager = new MongoManager();
        mongoManager.addDocument(document);
    }
}
