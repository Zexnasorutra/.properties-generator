package es.arturo.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.arturo.bean.MacBean;

public class LectorUtil {
	
	public static List<MacBean> readXLSX(String ruta) throws Exception{
		List<MacBean> macs = new ArrayList<MacBean>();
		
		File file = null;
		FileInputStream fis = null;

		try {
			file = new File(ruta);
			fis = new FileInputStream(file);
			@SuppressWarnings("resource")
			XSSFWorkbook workBook = new XSSFWorkbook(fis);
			XSSFSheet mySheet = workBook.getSheetAt(0);
			Iterator<Row> rows= mySheet.iterator();
			
			while(rows.hasNext()){
				macs.add(new MacBean(rows.next()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			try{
			fis.close();
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}
			
		}
		
		return macs;	
	} 

	
}
