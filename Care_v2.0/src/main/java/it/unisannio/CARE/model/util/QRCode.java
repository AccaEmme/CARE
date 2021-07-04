package it.unisannio.CARE.model.util;

import com.google.zxing.WriterException;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import org.json.simple.JSONObject;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class QRCode {
    String object;
    String identifier;
    String save_path = Constants.QR_CODES_SAVE_PATH;

    public QRCode(JSONObject objectToWrite){
        this.object = objectToWrite.toJSONString();
        this.identifier = objectToWrite.get("serial")
                .toString()
                .replaceAll("\\s+", "_")
                .toLowerCase();

    }
    public QRCode(String objectToWrite, String identifier){
        this.object = objectToWrite;
        this.identifier = identifier
                .replaceAll("\\s+", "_")
                .toLowerCase();
    }
    public QRCode(BloodBag objectToWrite){
        this.object = this.createBloodBagObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getSerial()
                .toString()
                .replaceAll("\\s+", "_")
                .toLowerCase();
    }

    public QRCode(BloodBagDAO objectToWrite){
        this.object = this.createBloodBagObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getSerial()
                .replaceAll("\\s+", "_")
                .toLowerCase();;
    }






    private JSONObject createBloodBagObject(BloodBag bag){
        JSONObject object = new JSONObject();
            object.put("serial",bag.getSerial());
     /*       object.put("group",bag.getBloodGroup().toString());
            object.put("creationDate",bag.getCreationDate());
            object.put("expirationDate",bag.getExpirationDate());
            object.put("donator",bag.getDonatorCF());
            object.put("state",bag.getBloodBagState());
            object.put("notes",bag.getNote());*/

        return object;
    }
    private JSONObject createBloodBagObject(BloodBagDAO bag){
        JSONObject object = new JSONObject();
        object.put("serial",bag.getSerial());
   /*     object.put("group",bag.getGroup());
        object.put("creationDate",bag.getCreationDate());
        object.put("expirationDate",bag.getExpirationDate());
        object.put("donator",bag.getDonator());
        object.put("state",bag.getState());
        object.put("notes",bag.getNotes());*/

        return object;
    }


    //important stuff
    public void createQRCode(){
        try {
            String qrCodeData = object;
            String filePath = this.save_path+identifier+"_"+new Date().getTime() +".png";
            String charset = "UTF-8"; // or "ISO-8859-1"
            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            BitMatrix matrix = new MultiFormatWriter().encode(
                    new String(qrCodeData.getBytes(charset), charset),
                    BarcodeFormat.QR_CODE, 200, 200, hintMap);
            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                    .lastIndexOf('.') + 1), new File(filePath));
            System.out.println("QR Code image created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Couldn't save the QR code.");
        } catch (WriterException e) {
            e.printStackTrace();
            System.err.println("Couldn't create the QR code.");
        }catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unexpected QR code error.");
        }
    }


    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }
}
