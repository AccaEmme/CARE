package it.unisannio.CARE.model.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositReportable implements Reportable {

	List<Reportable> reportabili=new ArrayList<>();
	
	public void saveReport() throws IOException {
		for(Reportable re : reportabili) {
			re.saveReport();
		}
	}
	
	public void add(Reportable r) {
		reportabili.add(r);
	}
	
}
