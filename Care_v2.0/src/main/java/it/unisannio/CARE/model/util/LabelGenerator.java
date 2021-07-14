package it.unisannio.CARE.model.util;

import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class LabelGenerator {
    private String htmlBody = "<html>\n" +
            " <head>\n" +
            "  <title>Etichetta Sacca</title>\n" +
            " </head>\n" +
            "<body>\n" +
            "<table border=\"2\" width=\"100%\" height=\"100%\"> \n" +
            "<tr>\n" +
            "  <td align=\"center\" width=\"50%\">\n" +
            "    QR CODE gigante<br>\n" +
            "    <img src=\"https://i.pinimg.com/originals/94/a2/8e/94a28ea35f6e76d9e38d63476466760a.jpg\" width=\"50%\">\n" +
            "  </td>\n" +
            "  <td align=\"center\" width=\"50%\">\n" +
            "    <table border=\"0\">\n" +
            "      <tr>\n" +
            "        <td>codice a barre gruppo sanguigno</td>\n" +
            "        <td>logo</td>\n" +
            "      </tr>\n" +
            "      <tr colspan=\"2\">\n" +
            "        <td>gruppo sanguigno</td>\n" +
            "      </tr>\n" +
            "    </table>\n" +
            "  </td>\n" +
            "</tr>\n" +
            "<tr>\n" +
            "  <td align=\"center\">Info creazione</td>\n" +
            "  <td align=\"center\">Info scadenza: <i>sta facendo la muffa</i></td>\n" +
            "</tr>\n" +
            "</table>\n" +
            "</body>\n" +
            "</html>";

    private BloodBagDAO bag;

    public LabelGenerator(BloodBagDAO bagDAO){
        this.bag = bagDAO;
    }

    public void renderHTML() throws IOException {
        int width = 400, height = 600;

        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);

        Graphics graphics = image.createGraphics();

        JEditorPane jep = new JEditorPane("text/html", this.htmlBody);
        jep.setSize(width, height);
        jep.print(graphics);

        ImageIO.write(image, "png", new File("Labels/"+this.bag.getSerial()+"-"+new Date().getTime()+".png"));
    }
}
