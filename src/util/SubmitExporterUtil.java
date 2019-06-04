package util;

import java.io.File;
import java.util.List;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import vo.Answer;


public class SubmitExporterUtil {
	public static File doExport(String filePath, List<Answer> ls) {
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(filePath));
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);

			Label label = new Label(0, 0, "班级");
			sheet.addCell(label);
			label = new Label(1, 0, "学号");
			sheet.addCell(label);
			label = new Label(2, 0, "姓名");
			sheet.addCell(label);
			label = new Label(3, 0, "提交时间");
			sheet.addCell(label);
			for (int i = 0; i < ls.size(); i++) {
				Answer s = (Answer) ls.get(i);
				label = new Label(0, i + 1, s.getStudent().getSclass());
				sheet.addCell(label);
				label = new Label(1, i + 1, s.getStudent().getNumber());
				sheet.addCell(label);
				label = new Label(2, i + 1, s.getStudent().getName());
				sheet.addCell(label);

				label = new Label(3, i + 1, s.getSubmitTime().toLocaleString());
				sheet.addCell(label);
			}
			workbook.write();
			workbook.close();
			return new File(filePath);
		} catch (java.io.IOException | WriteException e) {
		}
		return null;
	}
}
