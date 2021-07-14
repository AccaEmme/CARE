package it.unisannio.CARE.spring;

import it.unisannio.CARE.model.bloodBag.BloodGroup;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.CARE.model.util.LabelGenerator;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LabelGeneratorTest {
    @Test
    public void testGenerateHTMLImage() throws IOException, InterruptedException {
        BloodBagDAO dao = new BloodBagDAO();
        dao.setSerial("IT-AV201000-ANEG-20210713-0000");
        dao.setCreationDate(new Date().getTime());
        dao.setExpirationDate(new Date().getTime()+ Constants.SEVEN_DAYS_MILLIS);
        dao.setGroup(BloodGroup.ABneg.toString());

        LabelGenerator labelGenerator = new LabelGenerator(dao);

        labelGenerator.createLabel();
    }
}
