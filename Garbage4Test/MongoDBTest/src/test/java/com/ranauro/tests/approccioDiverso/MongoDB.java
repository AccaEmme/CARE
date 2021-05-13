package com.ranauro.tests.approccioDiverso;

import com.mongodb.*;

//non uso le classi scritte prima
public class MongoDB {
    public static MongoClient mongoClient;
    public static DB database;                  //crea un db nel client mongoClient.
    public static DBCollection test;            //crea una collezione nel db

    public static void main(String[] args) {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("MongoDB");
        test = database.getCollection("provetta");

        //DBObject object = new BasicDBObject("employee","bob").append("items", new int[]).append("table","table5");    modo brutto per aggiungere
        /*TestObj testObj = new TestObj();
        testObj.setMemberID("abc");
        testObj.setTimer(1);
        testObj.setXp(1234);
        test.insert(convert(testObj));*/


        DBObject query = new BasicDBObject("Timer",1);  //cerco l'elemento
        DBCursor cursor = test.find(query);
        System.out.println(cursor.next());
        System.out.println(cursor.next());

    }
    public static DBObject convert(TestObj testObj){
        return new BasicDBObject("XP", testObj.getXp()).append("Timer", testObj.getTimer()).append("memberID",testObj.getMemberID());
    }
}
