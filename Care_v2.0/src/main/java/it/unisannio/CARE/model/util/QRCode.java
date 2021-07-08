package it.unisannio.CARE.model.util;

import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.qrcode.QRCodeWriter;
import it.unisannio.CARE.model.bloodBag.BloodBag;
import it.unisannio.CARE.model.user.User;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import it.unisannio.ingsof20_21.group8.Care.Spring.UserDAO;
import org.json.simple.JSONObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;


import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * this class is used to generate all the QR codes needed for the CARE system
 */
public class QRCode {
    String object;
    String identifier;
    String save_path = Constants.QR_CODES_SAVE_PATH;

    private final String DIR = Constants.QR_CODES_SAVE_PATH;
    private final String ext = ".png";
    private String LOGO = "https://i.imgur.com/sbssf2U.png";
    private final int WIDTH = 500;
    private final int HEIGHT = 500;

    private Colors background = Colors.WHITE;
    private Colors foreground = Colors.BLACK;


    /**
     * save the QR code starting from a JSON object
     * @param objectToWrite the object to save on the QR code
     */
    public QRCode(JSONObject objectToWrite){
        this.identifier = objectToWrite.get("serial")
                .toString()
                .replaceAll("\\s+", "_")
                .toLowerCase();
        this.object = this.identifier;      //sbagliato! da correggere
    }

    /**
     * save the QR code starting from a string
     * @param objectToWrite the string to write on the QR code
     * @param identifier the identifier used as filename
     */
    public QRCode(String objectToWrite, String identifier){
        this.object = objectToWrite;
        this.identifier = identifier
                .replaceAll("\\s+", "_")
                .toLowerCase();
    }

    /**
     * empty constructor
     */
    public QRCode(){

    }

    /**
     * save the QR code starting from a BloodBag object
     * @param objectToWrite the object to save on the QR code
     */
    //tested
    public QRCode(BloodBag objectToWrite){
        this.object = objectToWrite.getSerial().getSerial(); //this.createBloodBagObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getSerial()
                .toString()
                .replaceAll("\\s+", "_")
                .toLowerCase();
    }

    /**
     * save the QR code starting from a BloodBagDAO
     * @param objectToWrite the object to save on the QR code
     */
    public QRCode(BloodBagDAO objectToWrite){
        this.object = objectToWrite.getSerial();//this.createBloodBagObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getSerial()
                .replaceAll("\\s+", "_")
                .toLowerCase();;
    }


    /**
     * save the QR code starting from UserDAO
     * @param objectToWrite  the object to write on the QR code
     */
    // ################## CREATE QR CODE USERS ###################
    public QRCode(UserDAO objectToWrite){
        JSONObject user = new JSONObject();
        user.put("username",objectToWrite.getUsername());
        user.put("email",objectToWrite.getEmail());
        this.object = user.toJSONString();//this.createUserObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getUsername();
    }

    /**
     * save the QR code starting from a User object
     * @param objectToWrite the object to write on the QR code
     */
    public QRCode(User objectToWrite){
        JSONObject user = new JSONObject();
        user.put("username",objectToWrite.getUsername());
        user.put("email",objectToWrite.getEmail());

        this.object = user.toJSONString();//this.createUserObject(objectToWrite).toJSONString();
        this.identifier = objectToWrite.getUsername();
    }


    /**
     * @param bag the BloodBag object
     * @return the JSON object to write
     */
    //bloodbag utils
    private JSONObject createBloodBagObject(BloodBag bag){
        JSONObject object = new JSONObject();
            object.put("serial",bag.getSerial());
        return object;
    }
    /**
     * @param bag the BloodBagDAO object
     * @return the JSON object to write
     */
    private JSONObject createBloodBagObject(BloodBagDAO bag){
        JSONObject object = new JSONObject();
            object.put("serial",bag.getSerial());
        return object;
    }

    //user utils

    /**
     * @param user the UserDAO object
     * @return the JSON object to write
     */
    private JSONObject createUserObject(UserDAO user){
        JSONObject object = new JSONObject();
            object.put("username",user.getUsername());
            object.put("email",user.getEmail());

        return object;
    }
    /**
     * @param user the User object
     * @return the JSON object to write
     */
    private JSONObject createUserObject(User user){
        JSONObject object = new JSONObject();
            object.put("username",user.getUsername());
            object.put("email",user.getEmail());
        return object;
    }

    /**
     * this method is used to create a QR code starting from the given parameters.
     * @Deprecated please don't use this method, use createQRCodeWithLogo instead.
     */
    @Deprecated
    public void createQRCodeOnly(){
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

    /**
     * this method is used to create a QR code starting from the given parameters.
     * It can create a QR code with a logo in the middle
     * @throws WriterException if the program couldn't write the QR code successfully
     */
    public void createQRCodeWithLogo() throws WriterException {
        // Create new configuration that specifies the error correction
        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {
            // init directory
            //cleanDirectory(DIR);
            initDirectory(DIR);
            // Create a qr code with the url as content and a size of WxH px
            bitMatrix = writer.encode(this.object, BarcodeFormat.QR_CODE, WIDTH, HEIGHT, hints);

            // Load QR image
            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, getMatrixConfig());

            // Load logo image
            BufferedImage overly = getOverly(LOGO);

            // Calculate the delta height and width between QR code and logo
            int deltaHeight = qrImage.getHeight() - overly.getHeight();
            int deltaWidth = qrImage.getWidth() - overly.getWidth();

            // Initialize combined image
            BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = (Graphics2D) combined.getGraphics();

            // Write QR code to new image at position 0/0
            g.drawImage(qrImage, 0, 0, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            // Write logo into combine image at position (deltaWidth / 2) and
            // (deltaHeight / 2). Background: Left/Right and Top/Bottom must be
            // the same space for the logo to be centered
            g.drawImage(overly, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);

            // Write combined image as PNG to OutputStream
            ImageIO.write(combined, "png", os);
            // Store Image
            Files.copy( new ByteArrayInputStream(os.toByteArray()), Paths.get(DIR + this.identifier+"_"+new Date().getTime()/*generateRandoTitle(new Random(), 9)*/ +ext), StandardCopyOption.REPLACE_EXISTING);

        } catch (WriterException e) {
            e.printStackTrace();
            //LOG.error("WriterException occured", e);
        } catch (IOException e) {
            e.printStackTrace();
            //LOG.error("IOException occured", e);
        }
    }

    /**
     * used to create the QR code overlay image
     * @param LOGO the logo address
     * @return the image of the logo
     * @throws IOException if the provided link contains no image
     */
    private BufferedImage getOverly(String LOGO) throws IOException {
        URL url = new URL(LOGO);
        return ImageIO.read(url);
    }

    /**
     * used to initialize the QR code file directory
     * @param DIR the QR code image directory
     * @throws IOException if the directory is not valid
     */
    private void initDirectory(String DIR) throws IOException {
        Files.createDirectories(Paths.get(DIR));
    }

    /**
     * used to wipe the QR code directoru
     * @param DIR the QR code image directory
     */
    private void cleanDirectory(String DIR) {
        try {
            Files.walk(Paths.get(DIR), FileVisitOption.FOLLOW_LINKS)
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            // Directory does not exist, Do nothing
        }
    }

    /**
     * used to create the QR code matrix
     * @return the QR code matrix configuration
     */
    private MatrixToImageConfig getMatrixConfig() {
        // ARGB Colors
        // Check Colors ENUM
        return new MatrixToImageConfig(this.foreground.getArgb(), this.background.getArgb());
    }

    /**
     * used to create a random title for the QR code
     * @param random the random object
     * @param length the length of the filename
     * @return the generated filename
     */
    private String generateRandoTitle(Random random, int length) {
        return random.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    /**
     * used to declare the possible QR code colors
     */
    public enum Colors {

        BLUE(0xFF40BAD0),
        RED(0xFFE91C43),
        PURPLE(0xFF8A4F9E),
        ORANGE(0xFFF4B13D),
        WHITE(0xFFFFFFFF),
        BLACK(0xFF000000);

        private final int argb;

        Colors(final int argb){
            this.argb = argb;
        }

        public int getArgb(){
            return argb;
        }
    }


    /**
     * used to get the object to write
     * @return the object to write
     */
    public String getObject() {
        return object;
    }

    /**
     * used to set the object to write
     * @param object the object to write
     */
    public void setObject(String object) {
        this.object = object;
    }

    /**
     * used to get the identifier
     * @return the identifier of the QR code
     */
    public String getIdentifier() {
        return identifier;
    }

    /*
     * used to set the identifier
     * @param identifier the identifier of the QR code
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * used to get the qr code save path
     * @return the QR code save path
     */
    public String getSave_path() {
        return save_path;
    }

    /**
     * used to set the qr code save path
     * @param save_path the QR code save path
     */
    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    /**
     * used to get the logo in the middle of the QR code
     * @return the Logo in the middle of the QR code
     */
    public String getLOGO() {
        return LOGO;
    }

    /**
     * used to set the logo in the middle of the QR code
     * @param LOGO the Logo in the middle of the QR code
     */
    public void setLOGO(String LOGO) {
        this.LOGO = LOGO;
    }

    /**
     * used to get the background color of the QR code
     * @return the background color
     */
    public Colors getBackground() {
        return background;
    }

    /**
     * used to set the background color of the QR code
     * @param background the background color
     */
    public void setBackground(Colors background) {
        this.background = background;
    }

    /**
     * used to set get foreground color of the QR code
     * @return the foreground color
     */
    public Colors getForeground() {
        return foreground;
    }

    /**
     * used to set the foreground color of the QR code
     * @param foreground the foreground color
     */
    public void setForeground(Colors foreground) {
        this.foreground = foreground;
    }
}
