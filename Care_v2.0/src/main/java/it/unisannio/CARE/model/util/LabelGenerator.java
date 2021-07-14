package it.unisannio.CARE.model.util;

import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LabelGenerator {
    private String bloodGroup;
    private String serial;
    private long creationDate;
    private long expirationDate;
    private long timestamp;



    public LabelGenerator(BloodBagDAO bagDAO){
        this.bloodGroup = bagDAO.getGroup();
        this.serial = bagDAO.getSerial();
        this.creationDate = bagDAO.getCreationDate();
        this.expirationDate = bagDAO.getExpirationDate();
        this.timestamp = new Date().getTime();
    }

    public void createLabel() throws InterruptedException, IOException {
        this.generateQRCode();
        TimeUnit.SECONDS.sleep(1);

        this.renderHTML();
        TimeUnit.SECONDS.sleep(1);

        this.mergeImages();
        TimeUnit.SECONDS.sleep(1);

    }

    private void mergeImages() throws IOException {
        //URL url = new URL("https://i.imgur.com/LS3mzLw.png");
        File file0 = new File("Labels/scrap/"+this.serial+"-"+this.timestamp+".png");  //immagine di sfondo: html
        BufferedImage im = ImageIO.read(file0);
        System.out.println("entro qui");
        //URL url2 = new URL("QR_codes/IT-AV201000-ANEG-20210714-0000_1626254144766.png");
        //File file = new File("QR_codes/IT-AV201000-ANEG-20210713-0000_1626274470375.png"); //solo di test

        File file = new File("QR_codes/"+this.serial+"_"+this.timestamp+".png"); //immagine di overlay: qr code
        BufferedImage im2 = ImageIO.read(file);
        Graphics2D g = im.createGraphics();
        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        /* al centro
        g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2,
                (im.getHeight() - im2.getHeight()) / 2, null);*/
        g.drawImage(im2, 25,
                50, null);

        g.dispose();

        /*JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JLabel(new ImageIcon(im)));
        f.pack();
        f.setVisible(true);*/


        ImageIO.write(im, "png", new File("Labels/"+this.serial+"-"+this.timestamp+".png"));
    }

    private void generateQRCode(){
        JSONObject object = new JSONObject();
        object.put("serial", this.serial);

        QRCode code = new QRCode(object,this.timestamp);

        code.createQRCode();
    }

    private void renderHTML() throws IOException {
        String htmlBody = "<p>&nbsp;</p>\n" +
                "<table style=\"border-collapse: collapse; width: 100.3%; height: 383px;\" border=\"1\">\n" +
                "<tbody>\n" +
                "<tr style=\"height: 169px;\">\n" +
                "<td style=\"width: 61.7699%; height: 169px;\">\n" +
                "<p>&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">.</p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">"+this.serial+"</p>\n" +
                "</td>\n" +
                "<td style=\"width: 57.0644%; height: 169px; text-align: center;\">\n" +
                "<h2><span style=\"color: #ff0000;\">"+this.bloodGroup+"</span></h2>\n" +
                "<h2>&nbsp;</h2>\n" +
                "<h2>ZEROpos</h2>\n" +
                "<p>------------------------------</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "<tr style=\"height: 168px;\">\n" +
                "<td style=\"width: 61.7699%; height: 214px;\">\n" +
                "<h2 style=\"text-align: center;\">Creation Date</h2>\n" +
                "<p style=\"text-align: center;\">&nbsp;"+this.creationDate+"</p>\n" +
                "<p style=\"text-align: center;\">datahuman</p>\n" +
                "</td>\n" +
                "<td style=\"width: 57.0644%; height: 214px;\">\n" +
                "<h2 style=\"text-align: center;\">Expiration Date</h2>\n" +
                "<p style=\"text-align: center;\">"+this.expirationDate+"&nbsp;</p>\n" +
                "<p style=\"text-align: center;\">datahuman</p>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        int width = 450, height = 600;

        /*BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);*/
        BufferedImage image = new BufferedImage(450, 600, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = image.createGraphics();

        JEditorPane jep = new JEditorPane("text/html", htmlBody);
        jep.setSize(width, height);
        jep.print(graphics);

        ImageIO.write(image, "png", new File("Labels/scrap/"+this.serial+"-"+this.timestamp+".png"));
    }
}
