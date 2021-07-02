package it.unisannio.CARE.spring;

import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.util.QRCode;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.text.ParseException;

public class QRCodeTester {
    @Test
    public void testCreateQRFromJSONObject(){
        /**
         * {
         *  "serial":"IT-NA205101-Aneg-20210615-0037",
         *  "group":"Bneg",
         *  "creationDate":965837967,
         *  "expirationDate":965837968,
         *  "donator":"CRSDLCER86BH0919",
         *  "state":"Available",
         *  "notes":"test note"
         * }*/
        BloodBagDAO bag = new BloodBagDAO();
            bag.setSerial("IT-NA205101-Aneg-20210615-0037");
            bag.setGroup("Bneg");
            bag.setCreationDate(965837967);
            bag.setExpirationDate(965837968);
            bag.setDonator("CRSDLCER86BH0919");
            bag.setState("Available");
            bag.setNotes("buonanott");

    JSONObject object = new JSONObject();
        object.put("serial",bag.getSerial());
        object.put("group",bag.getGroup());
        object.put("creationDate",bag.getCreationDate());
        object.put("expirationDate",bag.getExpirationDate());
        object.put("donator",bag.getDonator());
        object.put("state",bag.getState());
        object.put("notes",bag.getNotes());

        QRCode code = new QRCode(object);

        code.createQRCode();
    }

    @Test
    public void testCreateQRCodeFromBloodBagDao(){
        BloodBagDAO bag = new BloodBagDAO();
        bag.setSerial("IT-NA205101-Aneg-20210615-0037");
        bag.setGroup("Bneg");
        bag.setCreationDate(965837967);
        bag.setExpirationDate(965837968);
        bag.setDonator("CRSDLCER86BH0919");
        bag.setState("Available");
        bag.setNotes("buonanott");

        QRCode code = new QRCode(bag);
        code.createQRCode();
    }

    @Test
    public void testCreateQRCodeFromString(){
        String qr = "Peppiniello è incazzato";
        String id = "peppiniello_importunato";

        QRCode code = new QRCode(qr,id);
        code.createQRCode();
    }

    @Test
    public void testCreateQRCodeFromStringReplaceSpaces(){
        String qr = "Peppiniello è incazzato";
        String id = "peppiniello importunato";  //lo spazio viene rimpiazzato con '_'

        QRCode code = new QRCode(qr,id);
        code.createQRCode();
    }

    @Test
    public void testCreateQRCodeFromBloodBagException() throws ParseException {
        BloodBag bag = new BloodBag(BloodGroup.ABpos,"peppiniello");

        QRCode code = new QRCode(bag);
        code.createQRCode();
    }

    @Test
    public void testCreateQRCodeFromBloodBag() throws ParseException {
        BloodBag bag = new BloodBag(BloodGroup.ABpos,"CRSDLCER86BH7860");
        //NON DOVREBBE LANCIARE ECCEZIONE MA LA LANCIA, IL PROBLEMA è BLOOD BAG
        QRCode code = new QRCode(bag);
        code.createQRCode();
    }
}
