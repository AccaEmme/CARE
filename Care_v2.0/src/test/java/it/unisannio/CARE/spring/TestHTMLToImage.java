package it.unisannio.CARE.spring;

import it.unisannio.CARE.model.util.Constants;
import org.junit.Test;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//from  w  w w.  java  2  s . c o m
import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
//from  ww  w .j a v a  2  s  .c  o  m
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TestHTMLToImage {
    @Test
    public void testGenerate() throws IOException {
        String imageSource = "https://i.imgur.com/fFVTdb8.png";
        String bloodGroup = Constants.DEFAULT_NOT_AVAILABLE;
        String html = "<html>\n" +
                " <head>\n" +
                "  <title>Etichetta Sacca</title>\n" +
                " </head>\n" +
                "<body>\n" +
                "<table border=\"2\" width=\"100%\" height=\"100%\"> \n" +
                "<tr>\n" +
                "  <td align=\"center\" width=\"50%\">\n" +
                "    QR CODE gigante<br>\n" +
                "    <img src=\""+imageSource+"\" width=\"50%\">\n" +
                "  </td>\n" +
                "  <td align=\"center\" width=\"50%\">\n" +
                "    <table border=\"0\">\n" +
                "      <tr>\n" +
                "        <td>codice a barre gruppo sanguigno</td>\n" +
                "        <td>logo</td>\n" +
                "      </tr>\n" +
                "      <tr colspan=\"2\">\n" +
                "        <td>"+bloodGroup+"</td>\n" +
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
        int width = 400, height = 600;

        BufferedImage image = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice().getDefaultConfiguration()
                .createCompatibleImage(width, height);

        Graphics graphics = image.createGraphics();

        JEditorPane jep = new JEditorPane("text/html", html);
        jep.setSize(width, height);
        jep.print(graphics);

        ImageIO.write(image, "png", new File("Image.png"));
    }

    @Test
    public void imageOnTopOfAnother() throws IOException {
        URL url = new URL("https://i.imgur.com/LS3mzLw.png");
        BufferedImage im = ImageIO.read(url);
        //URL url2 = new URL("QR_codes/IT-AV201000-ANEG-20210714-0000_1626254144766.png");
        File file = new File("QR_codes/IT-AV201000-ANEG-20210713-0000_1626197678512.png");
        BufferedImage im2 = ImageIO.read(file);
        Graphics2D g = im.createGraphics();
        //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
        /* al centro
        g.drawImage(im2, (im.getWidth() - im2.getWidth()) / 2,
                (im.getHeight() - im2.getHeight()) / 2, null);*/
        g.drawImage(im2, 200,
                200, null);

        g.dispose();

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(new JLabel(new ImageIcon(im)));
        f.pack();
        f.setVisible(true);


        ImageIO.write(im, "png", new File("output.png"));
    }

}
