import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileIO {

	public static void main(String[] args) {
		
		String fileName="lotto.xls";
		
		try(FileInputStream fis = new FileInputStream(fileName)) {
			Workbook workbook=null; //삼항도 가능하답니다.
			if(fileName.endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis);
				System.out.println("so what");
			}
			else if(fileName.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis);
				System.out.println("so what2");
			}
			else {
				System.out.println("Sddsf");
			}
			Sheet sheet = workbook.getSheetAt(0); //0번째 시트
			
			for (Row row : sheet) {
				for (Cell cell : row) {
					// 셀 유형에 따라 데이터를 읽음
					switch (cell.getCellType()) {
					case STRING:
						System.out.print(cell.getStringCellValue() + "\t");
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							System.out.print(cell.getDateCellValue() + "\t");
						} else {
							System.out.print((int) cell.getNumericCellValue() + "\t");
						}
						break;
					case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + "\t");
						break;
					case FORMULA://수식
						System.out.print(cell.getCellFormula() + "\t");
						break;
					default:
						System.out.print(" ");
					}
				}
				System.out.println(); // 행을 출력한 후 줄바꿈
			}
	}
		 catch (FileNotFoundException e) {
			 System.out.println("Sdf");
		 }
				
			
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	
		}
	}
