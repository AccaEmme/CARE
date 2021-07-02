/**
 * @author giuliano ranauro
 * Date: 02/07/2021 11:09
 * Ide: Intellij
 * JDK: 1.8
 * @version 1.0
 */
package it.unisannio;

import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

public class ReadQRFromWebcam {
    public static void main(String[] args) throws NotFoundException {
        Webcam webcam = Webcam.getDefault(); // non-default (e.g. USB) webcam can be used too
        webcam.open();

        Result result = null;
        BufferedImage image = null;

        if (webcam.isOpen()) {
            if ((image = webcam.getImage()) == null) {
            }

            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            try {
                result = new MultiFormatReader().decode(bitmap);
            } catch (NotFoundException e) {
                // fall thru, it means there is no QR code in image
            }
        }

        if (result != null) {
            System.out.println("QR code data is: " + result.getText());
        }
    }
}
