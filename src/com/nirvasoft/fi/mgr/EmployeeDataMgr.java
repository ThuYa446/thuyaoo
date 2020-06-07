package com.nirvasoft.fi.mgr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nirvasoft.fi.dao.EmployeeTaxPayerDao;
import com.nirvasoft.fi.shared.Employee;
import com.nirvasoft.fi.shared.TaxPaymentEmployee;
import com.nirvasoft.fi.shared.TaxPaymentRegisteration;
import com.nirvasoft.fi.shared.TaxPeriod;
import com.nirvasoft.fi.util.hibernateUtils;

public class EmployeeDataMgr {

	public static boolean saveEmployeeData(String fullFilePath) throws IOException, ParseException{
		XSSFWorkbook workbook = null;
		EntityManager entityManager = hibernateUtils.getEntityManager();
		try{
			File source = new File(fullFilePath);
			FileInputStream fis = new FileInputStream(source);
			workbook = new XSSFWorkbook(fis);
			
			XSSFSheet employee = workbook.getSheetAt(0);
			boolean flag = readEmployee(employee,entityManager);
			if(flag){
				return true;
			}
			entityManager.clear();
			entityManager.close();
		}catch(FileNotFoundException e){
			e.getMessage();
			
		}
		return false;
	}
	
	public static boolean readEmployee(XSSFSheet sheet,EntityManager entityManager){
		Iterator<Row> itr = sheet.iterator(); 
		String employerName = "";
		String tinNumber = "";
		int i = 4;
		while(itr.hasNext()){
			Row row = itr.next();
			Employee emp = new Employee();
			TaxPeriod txpr = new TaxPeriod();
			TaxPaymentRegisteration txrg = new TaxPaymentRegisteration();
			try{
				if(row.getRowNum()== 0 || row.getRowNum() == 3 || row == null){
					while(true)
					 break;
				}else if(row.getRowNum() == 1){
				  employerName = row.getCell(3).toString().trim();
				}else if(row.getRowNum()==2){
					tinNumber = row.getCell(3).toString().trim();
				}else{
					String record = row.getCell(0).toString().trim() ;
		    		if( record.equals("")){
		    			break;
		    		}
		    		emp.setNrc(row.getCell(1).toString().trim());
		    		emp.setPassPort(row.getCell(2).toString().trim());
		    		emp.setEmployeeId(classifyEmpId(row.getCell(1).toString().trim(), row.getCell(2).toString().trim()));
		    		emp.setName(row.getCell(3).toString().trim());
		    		emp.setCurrentAdd1(row.getCell(5).toString().trim());
		    		emp.setCurrentAdd2(row.getCell(5).toString().trim());
		    		emp.setCurrentAdd3(row.getCell(5).toString().trim());
		    		if(EmployeeTaxPayerDao.saveEmployee(emp, entityManager)){
		    			if(readTaxPeriod(row, entityManager,txpr)){
		    				if(readTaxPaymentRegisteration(entityManager, tinNumber,txrg)){
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txpr, txrg, emp)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}
		    				}else{
		    					TaxPaymentRegisteration txpreg = EmployeeTaxPayerDao.getEmployeeByTaxPayerID(txrg.getTaxPayerID(), entityManager);
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txpr, txpreg, emp)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}
		    				}
		    			}else{
		    				TaxPeriod txprd = EmployeeTaxPayerDao.getTaxPeriodByPeriodCode(txpr.getPeriodCode(), entityManager);
		    				if(readTaxPaymentRegisteration(entityManager, tinNumber,txrg)){
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txprd, txrg, emp)){
		    						System.out.println("Read Line "+i+"Success");
		    						i++;
		    					}
		    				}else{
		    					TaxPaymentRegisteration txpreg = EmployeeTaxPayerDao.getEmployeeByTaxPayerID(txrg.getTaxPayerID(), entityManager);
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txprd, txpreg, emp)){
		    						System.out.println("Read Line "+i+"Success");
		    						i++;
		    					}
		    				}
		    			}
		    		}else{
		    			Employee empl = EmployeeTaxPayerDao.getEmployeeByEmpId(emp.getEmployeeId(), entityManager);
		    			if(readTaxPeriod(row, entityManager,txpr)){
		    				if(readTaxPaymentRegisteration(entityManager, tinNumber,txrg)){
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txpr, txrg, empl)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}
		    				}else{
		    					TaxPaymentRegisteration txpreg = EmployeeTaxPayerDao.getEmployeeByTaxPayerID(txrg.getTaxPayerID(), entityManager);
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txpr, txpreg, empl)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}
		    				}
		    			}else{
		    				TaxPeriod txprd = EmployeeTaxPayerDao.getTaxPeriodByPeriodCode(txpr.getPeriodCode(), entityManager);
		    				if(readTaxPaymentRegisteration(entityManager, tinNumber,txrg)){
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txprd, txrg, empl)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}
		    				}else{
		    					TaxPaymentRegisteration txpreg = EmployeeTaxPayerDao.getEmployeeByTaxPayerID(txrg.getTaxPayerID(), entityManager);
		    					if(readTaxPaymentEmployee(row, entityManager, employerName, txprd, txpreg, empl)){
		    						System.out.println("Read Line "+i+" Success");
		    						i++;
		    					}else{
		    						System.out.println("Employee Data at Row "+i+" is Updated!");
		    						i++;
		    						continue;
		    					}
		    				}
		    			}
		    		}
				}
			}catch(Exception e){
				return false;
			}
		}
		return true;
	}
	
	private static boolean readTaxPeriod(Row row,EntityManager entityManager,TaxPeriod txpr){
		txpr.setPeriodCode(row.getCell(7).toString().trim());
		if(EmployeeTaxPayerDao.saveTaxPeriod(txpr, entityManager)){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean readTaxPaymentRegisteration(EntityManager entityManager,String tinNo,TaxPaymentRegisteration txrg){
		txrg.setTaxPayerID(tinNo);
		if(EmployeeTaxPayerDao.saveTaxPaymentRegisteration(txrg, entityManager)){
			return true;
		}else{
			return false;
		}
	}
	
	private static boolean readTaxPaymentEmployee(Row row,EntityManager entityManager,String employerName,TaxPeriod txpr,TaxPaymentRegisteration txrg,Employee emp){
		TaxPaymentEmployee txpEmp = new TaxPaymentEmployee();
		txpEmp.setEmpl(emp);
		txpEmp.setTxpr(txpr);
		txpEmp.setTxrg(txrg);
		txpEmp.setEmployerName(employerName);
		txpEmp.setRank(row.getCell(4).toString().trim());
		txpEmp.setEndDatePeriod(convertStringToDate(row.getCell(6).toString().trim()));
		txpEmp.setSalary(Double.parseDouble(row.getCell(8).toString().trim()));
		txpEmp.setClaimAmt(Double.parseDouble(row.getCell(9).toString().trim()));
		txpEmp.setTaxAmt(Double.parseDouble(row.getCell(10).toString().trim()));
		txpEmp.setRemark(row.getCell(11).toString().trim());
		if(EmployeeTaxPayerDao.saveTaxPaymentEmployee(txpEmp, entityManager)){
			return true;
		}else{
			return false;
		}
	}
	private static String classifyEmpId(String nrc,String passport){
		String employeeId = "";
		if(nrc.equals("-") || nrc.equals("")){
			employeeId = passport;
		}else if((passport.equals("-") || passport.equals(""))||(!nrc.equals("-") && (!passport.equals("-")))){
			employeeId = nrc;
		}
		return employeeId;
	}
	
	private static Date convertStringToDate(String date){
		Date resultDate = null;
		try{
			String[] dateformat = date.split("-");
			String day = "";
			String month = "";
			String year = "";
			if(dateformat.length == 3){
				SimpleDateFormat smpDate = new SimpleDateFormat("dd-MM-yyyy");
				day = dateformat[0];
				month = dateformat[1];
				year = dateformat[2];
				resultDate = smpDate.parse(day+"-"+month+"-"+year);
			}
		}catch(ParseException e){
			e.getMessage();
		}
		return resultDate;
	}
}
