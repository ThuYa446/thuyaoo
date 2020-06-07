package com.nirvasoft.fi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.mgr.KeyGenerateMgr;
import com.nirvasoft.fi.shared.Employee;
import com.nirvasoft.fi.shared.TaxPaymentEmployee;
import com.nirvasoft.fi.shared.TaxPaymentRegisteration;
import com.nirvasoft.fi.shared.TaxPeriod;

public class EmployeeTaxPayerDao {
	
	@SuppressWarnings("unchecked")
	public static boolean isEmployeeExist(String empId,EntityManager entityManager){
		List<Employee> employeeList = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from Employee emp where emp.employeeId = :employee";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("employee", empId);
	    	employeeList = query.getResultList();
	    	entityManager.getTransaction().commit();
	    	if(employeeList.size()>0)
	    		return true;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isTaxPaymentRegistrationExist(String txpid,EntityManager entityManager){
		List<TaxPaymentRegisteration> txpPmtRegList = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from TaxPaymentRegisteration txpPmtReg where txpPmtReg.taxPayerID = :txPyId";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("txPyId", txpid);
	    	txpPmtRegList = query.getResultList();
	    	entityManager.getTransaction().commit();
	    	if(txpPmtRegList.size()>0)
	    		return true;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isTaxPeriodExist(String periodCode,EntityManager entityManager){
		List<TaxPaymentRegisteration> txpPmtRegList = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from TaxPeriod txpPeriod where txpPeriod.periodCode = :pCode";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("pCode", periodCode);
	    	txpPmtRegList = query.getResultList();
	    	entityManager.getTransaction().commit();
	    	if(txpPmtRegList.size()>0)
	    		return true;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isTaxPaymentEmpExist(TaxPaymentEmployee txpEmp,EntityManager entityManager){
		List<TaxPaymentEmployee> txpEmpList = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from TaxPaymentEmployee txpemp where txpemp.txrg = :txrg and txpemp.txpr = :txpr and txpemp.empl = :empl";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("txrg", txpEmp.getTxrg());
	    	query.setParameter("txpr", txpEmp.getTxpr());
	    	query.setParameter("empl", txpEmp.getEmpl());
	    	txpEmpList = query.getResultList();
	    	entityManager.getTransaction().commit();
	    	if(txpEmpList.size()>0)
	    		return true;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
		}
		return false;
	}
	
	public static boolean saveEmployee(Employee employee,EntityManager entityManager){
		try{
			if(!isEmployeeExist(employee.getEmployeeId(), entityManager)){
				employee.setSyskey(generateSysKey("Employee",1,entityManager));
				entityManager.getTransaction().begin( );
				entityManager.persist(employee);
				entityManager.getTransaction().commit();
				return true;
			}else{
				Employee updateEmployee = getEmployeeByEmpId(employee.getEmployeeId(), entityManager);
				entityManager.getTransaction().begin( );
				updateEmployee.setName(employee.getName());
				updateEmployee.setCurrentAdd1(employee.getCurrentAdd1());
				updateEmployee.setCurrentAdd2(employee.getCurrentAdd2());
				updateEmployee.setCurrentAdd3(employee.getCurrentAdd3());
				entityManager.getTransaction().commit();
				return false;
			}
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback();entityManager.close();
			return false;
		}
	}
	
	public static boolean saveTaxPeriod(TaxPeriod taxPeriod,EntityManager entityManager){
		try{
			if(!isTaxPeriodExist(taxPeriod.getPeriodCode(), entityManager)) {
			 taxPeriod.setSyskey(generateSysKey("TaxPeriod", 1, entityManager));
			 entityManager.getTransaction().begin( );
			 entityManager.persist(taxPeriod);
			 entityManager.getTransaction().commit();
			 return true;
			}
//		  }else{
//			  TaxPeriod updateTxp = getTaxPeriodByPeriodCode(taxPeriod.getPeriodCode(), entityManager);
//			  entityManager.getTransaction().begin( );
//			  updateTxp.setPeriodCode(taxPeriod.getPeriodCode());
//			  entityManager.getTransaction().commit();
//			  return false;
//		  }
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback();entityManager.close();
			return false;
		}
		return false;
	}
	
	public static boolean saveTaxPaymentRegisteration(TaxPaymentRegisteration taxPmtReg,EntityManager entityManager){
		try{
			if(!isTaxPaymentRegistrationExist(taxPmtReg.getTaxPayerID(), entityManager)){
				taxPmtReg.setSyskey(generateSysKey("TaxPaymentRegisteration", 1, entityManager));
				entityManager.getTransaction().begin( );
				entityManager.persist(taxPmtReg);
				entityManager.getTransaction().commit();
				return true;
			}
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback();entityManager.close();
			return false;
		}
		return false;
	}
	
	public static boolean saveTaxPaymentEmployee(TaxPaymentEmployee txpPmtEmp,EntityManager entityManager){
		try{
			if(!isTaxPaymentEmpExist(txpPmtEmp, entityManager)){
			 txpPmtEmp.setSyskey(generateSysKey("TaxPaymentEmployee", 1, entityManager));
			 entityManager.getTransaction().begin( );
			 entityManager.persist(txpPmtEmp);
			 entityManager.getTransaction().commit();
			 return true;
			}else{
				TaxPaymentEmployee updateTxpEmp = getTaxPaymentEmployee(txpPmtEmp, entityManager);
				if(updateTxpEmp!= null)
					entityManager.getTransaction().begin( );
					updateTxpEmp.setEndDatePeriod(txpPmtEmp.getEndDatePeriod());
					updateTxpEmp.setSalary(txpPmtEmp.getSalary());
					updateTxpEmp.setClaimAmt(txpPmtEmp.getClaimAmt());
					updateTxpEmp.setTaxAmt(txpPmtEmp.getTaxAmt());
					updateTxpEmp.setEmployerName(txpPmtEmp.getEmployerName());
					updateTxpEmp.setRemark(txpPmtEmp.getRemark());
					entityManager.getTransaction().commit();
				return false;
			}
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback();entityManager.close();
			return false;
		}
	}
	
	public static Employee getEmployeeByEmpId(String nrc,EntityManager entityManager){
		Employee employee = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from Employee emp where emp.employeeId = :employeeId";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("employeeId", nrc);
	    	employee = (Employee)query.getSingleResult();
	    	entityManager.getTransaction().commit();
	    	if(employee != null)
	    		return employee;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
			return employee = null;
		}
		return employee;
	}
	
	public static TaxPaymentRegisteration getEmployeeByTaxPayerID(String txpid,EntityManager entityManager){
		TaxPaymentRegisteration txpPmtReg = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from TaxPaymentRegisteration txpPmtReg where txpPmtReg.taxPayerID = :txPyId";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("txPyId", txpid);
	    	txpPmtReg = (TaxPaymentRegisteration)query.getSingleResult();
	    	entityManager.getTransaction().commit();
	    	if(txpPmtReg != null)
	    		return txpPmtReg;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
			return txpPmtReg = null;
		}
		return txpPmtReg;
	}
	
	public static TaxPeriod getTaxPeriodByPeriodCode(String periodCode,EntityManager entityManager){
		TaxPeriod txpPeriod = null;
		try{
			entityManager.getTransaction().begin( );
	    	String hql = "from TaxPeriod txpPeriod where txpPeriod.periodCode = :pCode";
	    	Query query = entityManager.createQuery(hql);
	    	query.setParameter("pCode", periodCode);
	    	txpPeriod = (TaxPeriod)query.getSingleResult();
	    	entityManager.getTransaction().commit();
	    	if(txpPeriod != null)
	    		return txpPeriod;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
			return txpPeriod = null;
		}
		return txpPeriod;
	}
	
	public static TaxPaymentEmployee getTaxPaymentEmployee(TaxPaymentEmployee txpEmp,EntityManager entityManager){
		TaxPaymentEmployee txpEmpl = null;
		try{
			entityManager.getTransaction().begin( );
			String hql = "from TaxPaymentEmployee txpEmp where txpEmp.txrg = :txrg and txpEmp.txpr = :txpr and txpEmp.empl = :empl";
			Query query = entityManager.createQuery(hql);
			query.setParameter("txrg", txpEmp.getTxrg());
			query.setParameter("txpr", txpEmp.getTxpr());
			query.setParameter("empl", txpEmp.getEmpl());
			txpEmpl = (TaxPaymentEmployee)query.getSingleResult();
			entityManager.getTransaction().commit();
			if(txpEmpl != null)
	    	  return txpEmpl;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
			return txpEmpl = null;
		}
		return txpEmpl;
	}
	
	@SuppressWarnings("unchecked")
	public static List<TaxPaymentEmployee> getTXPEmpByTextSearch(EntityManager entityManager,String text){
		List<TaxPaymentEmployee> txpEmpList = null;
		try{
			org.apache.lucene.search.Query searchQuery = null;
			entityManager.getTransaction().begin( );
			FullTextEntityManager fullTextEntityManager =Search.getFullTextEntityManager(entityManager);
			
			QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(TaxPaymentEmployee.class).get();
			if(text.contains(" ")){
				searchQuery = queryBuilder.keyword()
						.onFields("employerName","empl.name","txpr.periodCode","txrg.taxPayerID").matching(text).createQuery();
			}else{
				searchQuery = queryBuilder.keyword().wildcard()
						.onFields("employerName","empl.name","txpr.periodCode","txrg.taxPayerID").matching(text+"*").createQuery();
			}
			
			fullTextEntityManager.createIndexer().startAndWait();
			Query hql = fullTextEntityManager.createFullTextQuery(searchQuery,TaxPaymentEmployee.class );
			txpEmpList= hql.getResultList();
			entityManager.getTransaction().commit();
			if(txpEmpList.size()>0)
				return txpEmpList;
			else
				txpEmpList = null;
				return txpEmpList;
		}catch(Exception error){
			error.printStackTrace();
			if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
			return txpEmpList = null;
		}
	}
	
	public static Long generateSysKey(String tableName,int status,EntityManager entityManager){
		Result sysKey = new Result();
		sysKey = KeyGenerateMgr.getKeyDataMgr(tableName, status, entityManager);
		long result = Long.parseLong(sysKey.getKeyString());
		return result;
	}
}
