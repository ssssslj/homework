package util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class FileUploadUtil {

	public static boolean fileUpload(File upload, String uploadFileName, String filePath) {
		//uploadFileName°üÀ¨ºó×ºÃû
		File saveDir;
		File saveFile = null;
		if (upload != null) {
			saveDir = new File(filePath);
			if (!saveDir.exists())
				saveDir.mkdirs();
			else {
				saveDir.delete();
				saveDir.mkdirs();
			}
			saveFile = new File(saveDir, uploadFileName);
			try {
				FileUtils.copyFile(upload, saveFile);
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		if (saveFile.exists()) {
			return true;
		} else {
			return false;
		}

	}

}
