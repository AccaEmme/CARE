package it.unisannio.CARE.spring;

import com.google.zxing.WriterException;
import it.unisannio.CARE.model.util.NewQRCode;
import org.junit.Test;

public class NewQRCodeTester {
    @Test
    public void testQRCode() throws WriterException {
        NewQRCode qrCode = new NewQRCode();
        qrCode.generate();
    }
}
