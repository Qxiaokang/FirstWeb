package com.xk.utils;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.spi.XmlWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.XMLFilter;
/**
 * @author Admin
 * xml analysis tool
 */
public class ReadXml {
	public static void main(String[] args) {
		new ReadXml().readXml("D:/20112/web.xml");
		new ReadXml().writeXml("D:/20112/userlist.xml");
	}
	//DOM4 read xml file  (结合了DOM和SAX的优点)
	public void readXml(String filename) {
		// 解析器
		SAXReader saxReader = new SAXReader();
		File file = new File(filename);
		try {
			//构建树形结构并返回
			Document document = saxReader.read(file);
			//获得根元素
			Element element=document.getRootElement();
			
		    List list_sevlet=element.elements("servlet");
		    List list_mappring=element.elements("servlet-mapping");
		    List list_e=element.elements("error-page");
		    paseSevlet(list_sevlet);
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
	}
	//DOM4  pase list
	public void paseSevlet(List list){
		Iterator iterator=list.iterator();
		while (iterator.hasNext()) {
			Element element=(Element) iterator.next();
			//element.attributes();//获取元素所有属性
			System.out.println("name:"+element.elementText("servlet-name"));
			System.out.println("class:"+element.elementText("servlet-class"));
			//servlet中的嵌套
			List<Element>	list1 =element.elements("init-param");
			   Iterator<Element> iterator2=list1.iterator();
			   while (iterator2.hasNext()) {
				Element element2 = (Element) iterator2.next();
				System.err.println("init-param:"+element2.elementText("param-name"));
				System.err.println("init-param:"+element2.elementText("param-value"));
			}
		}
		
	}
	
	//DOM4  Write xml file
	public void writeXml(String pathString){
		String st[]={"id","name","pwd","city"};
		String ss[][]={{"1","sz1111","123457","深圳"},
				{"2","sz2222","123457","深圳"},
				{"3","sz3333","123457","深圳"}};
		try {
			
		//创建一个空的文本对象
		Document document=DocumentHelper.createDocument();
		//创建要元素
		Element element=document.addElement("userlist");
		for (String[] users : ss) {
			Element e=element.addElement("user");
			Element en=e.addElement("name");
			Element ep=e.addElement("pwd");
			Element ec=e.addElement("city");
			e.addAttribute("id", users[0]);
			en.setText(users[1]);
			ep.setText(users[2]);
			ec.setText(users[3]);
		}
		FileWriter fileWriter=new FileWriter(pathString);
		//指定XML文件的输出格式
		OutputFormat outputFormat=OutputFormat.createCompactFormat();
		outputFormat.setEncoding("utf-8");
		//写出XML文件到操作系统
		XMLWriter xmlWriter=new XMLWriter(fileWriter,outputFormat);
		xmlWriter.write(document);
		xmlWriter.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
