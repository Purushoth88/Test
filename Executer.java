import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.jsonCofig.BuilderExecution;

public class Executer {

	public static void main(String[] aa) throws InvalidFormatException,
			InterruptedException, IOException {
		BuilderExecution.createExcel();
		try {

		} catch (Exception e) {
			e.printStackTrace();

		}
		BuilderExecution.closeExcel();
	}
}
