package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		
		String path=".\\testData\\OpenCartLoginTestData.xlsx";
		
		ExcelUtility xlutil=new ExcelUtility(path);
		int totalrows=xlutil.getRowCount("sheet1");
		int totalcols=xlutil.getCellCount("sheet1", 1);
		
		String logindata[][]=new String [totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++) {//1 //read total data from xl storing in two dimensional array
			for(int j=0;j<totalcols;j++) {//0 i is row j is column
				logindata[i-1][j]=xlutil.getCellData("sheet1", i, j);//1,0
			}
		}
		return logindata;//two dimension array
	}

}
