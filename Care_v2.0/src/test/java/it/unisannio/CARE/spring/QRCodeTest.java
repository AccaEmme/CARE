package it.unisannio.CARE.spring;

import com.google.zxing.WriterException;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.exceptions.NullPasswordException;
import it.unisannio.CARE.model.exceptions.UserException;
import it.unisannio.CARE.model.exceptions.lllegalEmailException;
import it.unisannio.CARE.model.user.User;
import it.unisannio.CARE.model.util.Password;
import it.unisannio.CARE.model.util.QRCode;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;

import org.json.simple.JSONObject;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertThrows;

/**
 * JUnit test for Qrcode.
 */

public class QRCodeTest { 
    /**
     * this method saves a QR code starting from a BloodBagDAO object.
     */
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

    /**
     * this method creates a QR code starting from the BloodBagDAO
     */
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

    /**
     * this method creates a QR code starting from a content and an identifier
     */
    @Test
    public void testCreateQRCodeFromString(){
        String qr = "Peppiniello ?? incazzato";
        String id = "peppiniello_importunato";

        QRCode code = new QRCode(qr,id);
        code.createQRCode();
    }

    /**
     * this method creates a QR code starting from a content and an identifier with space
     */
    @Test
    public void testCreateQRCodeFromStringReplaceSpaces(){
        String qr = "Peppiniello ?? incazzato";
        String id = "peppiniello importunato";  //lo spazio viene rimpiazzato con '_'

        QRCode code = new QRCode(qr,id);
        code.createQRCode();
    }


    /**
     * this method creates a QR code starting from a BloodBag
    */
    @Test
    public void testCreateQRCodeFromBloodBag() throws ParseException {
        BloodBag bag = new BloodBag(BloodGroup.ABpos,"RNRGLN99P08A783G");
        QRCode code = new QRCode(bag);
        code.createQRCode();
    }

    /**
	* Invalid creation of the QRCode class constructor
	*/
    @Test 
    public void invalidTestCreateQRCodeFromBloodBagCF(){
        assertThrows(Exception.class, () -> {
                    BloodBag bag = new BloodBag(BloodGroup.ABpos,"peppiniello");

                    QRCode code = new QRCode(bag);
                    code.createQRCode();
                }
        );
    }
    
    /**
	* Invalid creation of the QRCode class constructor
	*/
    @Test 
    public void invalidTestCreateQRCodeFromBloodBagGroup(){
        assertThrows(Exception.class, () -> {
                    BloodBag bag = new BloodBag(BloodGroup.valueOf("peppiniello"),"RNRGLN99P08A783G");

                    QRCode code = new QRCode(bag);
                    code.createQRCode();
                }
        );
    }
    
    /**
	* Invalid creation of the QRCode class constructor
	*/
    @Test 
    public void invalidTestCreateQRCodeFromBloodBagBoth(){
        assertThrows(Exception.class, () -> {
                    BloodBag bag = new BloodBag(BloodGroup.valueOf("peppiniello"),"peppiniello");

                    QRCode code = new QRCode(bag);
                    code.createQRCode();
                }
        );
    }



    // user tests
    @Test
    public void testCreateQRCodeFromUserDAO(){
        UserDAO userDAO = new UserDAO();
            userDAO.setUsername("peppiniello");
            userDAO.setEmail("peppiniello99@gmail.com");

        QRCode qrCode = new QRCode(userDAO);
            qrCode.createQRCode();
    } 

    @Test
    public void testCreateQRCodeFromUser() throws NullPasswordException, UserException, lllegalEmailException {
        User user = new User("peppiniello99", new Password("PeppinielloPW9+"));
            user.setEmail("peppiniello99@gmail.com");
        QRCode code = new QRCode(user);
            code.createQRCode();
    }

    /* @TODO: ?? stato tolta la possibilit?? di creare il QRCode con logo, chiss?? se momentaneamente o per sempre.
    @Test
    public void createQRCodeWithLogo() throws WriterException, ParseException {
        BloodBagDAO bag = new BloodBagDAO();
        bag.setSerial("IT-NA205101-Aneg-20210615-0037");
        bag.setGroup("Bneg");
        bag.setCreationDate(965837967);
        bag.setExpirationDate(965837968);
        bag.setDonator("CRSDLCER86BH0919");
        bag.setState("Available");
        bag.setNotes("buonanott");

        QRCode code = new QRCode(bag);
        code.createQRCodeWithLogo();
    }
    */

}
