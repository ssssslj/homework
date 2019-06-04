package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class ConfigUtil {
	
	public static int pageSize = 5;
	
	public static void writeConfig(File file, Config1 config) {
		Document document = org.dom4j.DocumentHelper.createDocument();
		Element configElement = document.addElement("config");
		Element intervalElement = configElement.addElement("interval");
		intervalElement.addAttribute("desc", config.getInterval_desc());
		intervalElement.setText(Integer.toString(config.getInterval()));
		Element pagesizeElement = configElement.addElement("pagesize");
		pagesizeElement.addAttribute("desc", config.getPagesize_desc());
		pagesizeElement.setText(Integer.toString(config.getPagesize()));
		Element timegapElement = configElement.addElement("timegap");
		timegapElement.addAttribute("desc", config.getTimegap_desc());
		timegapElement.setText(Integer.toString(config.getTimegap()));
		Element minfilesizeElement = configElement.addElement("minfilesize");
		minfilesizeElement.addAttribute("desc", config.getMinfilesize_desc());
		minfilesizeElement.setText(Integer.toString(config.getMinfilesize()));
		Element maxfilesizeElement = configElement.addElement("maxfilesize");
		maxfilesizeElement.addAttribute("desc", config.getMaxfilesize_desc());
		maxfilesizeElement.setText(Integer.toString(config.getMaxfilesize()));
		Element candeleteElement = configElement.addElement("candelete");
		candeleteElement.addAttribute("desc", config.getCandelete_desc());
		candeleteElement.setText(Boolean.toString(config.isCandelete()));
		try {
			FileWriter fileWriter = new FileWriter(file);
			OutputFormat xmlFormat = new OutputFormat();
			xmlFormat.setEncoding("UTF-8");
			xmlFormat.setNewlines(true);
			xmlFormat.setLineSeparator("\r\n");
			xmlFormat.setIndent(true);
			xmlFormat.setIndent("\t");

			XMLWriter xmlWriter = new XMLWriter(fileWriter, xmlFormat);
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Config1 parseConfig(File file) {
		Config1 config = new Config1();
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding("GB2312");// 这里设置文件编码
		try {
			Document document = saxReader.read(new FileInputStream(file));
			Element rootElement = document.getRootElement();
			Element element = rootElement.element("interval");
			String desc = element.attributeValue("desc");
			config.setInterval_desc(desc);
			String value = element.getText().trim();
			config.setInterval(Integer.parseInt(value));
			element = rootElement.element("pagesize");
			desc = element.attributeValue("desc");
			config.setPagesize_desc(desc);
			value = element.getText().trim();
			config.setPagesize(Integer.parseInt(value));
			element = rootElement.element("timegap");
			desc = element.attributeValue("desc");
			config.setTimegap_desc(desc);
			value = element.getText().trim();
			config.setTimegap(Integer.parseInt(value));
			element = rootElement.element("minfilesize");
			desc = element.attributeValue("desc");
			config.setMinfilesize_desc(desc);
			value = element.getText().trim();
			config.setMinfilesize(Integer.parseInt(value));
			element = rootElement.element("maxfilesize");
			desc = element.attributeValue("desc");
			config.setMaxfilesize_desc(desc);
			value = element.getText().trim();
			config.setMaxfilesize(Integer.parseInt(value));
			element = rootElement.element("candelete");
			desc = element.attributeValue("desc");
			config.setCandelete_desc(desc);
			value = element.getText().trim();
			config.setCandelete(Boolean.parseBoolean(value));
		} catch (FileNotFoundException | DocumentException localFileNotFoundException) {
			localFileNotFoundException.printStackTrace();
		}
		return config;
	}
}
