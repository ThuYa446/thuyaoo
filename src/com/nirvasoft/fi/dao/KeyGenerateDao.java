package com.nirvasoft.fi.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.shared.KeyData;




public class KeyGenerateDao {

	@PersistenceContext
	public KeyData getKeyData(String objectName, int keyDataType,EntityManager entityManager){
	  KeyData keydata = new KeyData();
	  try{
		  entityManager.getTransaction().begin( );
		  String hql = "from KeyData kdata where kdata.objectName = :ObjectName and kdata.keyDataType = :keyDataType and kdata.status = :status";
		  Query query = entityManager.createQuery(hql);
		  query.setParameter("ObjectName", objectName);
	      query.setParameter("keyDataType", keyDataType);
	      query.setParameter("status",1);
	      keydata = (KeyData)query.getSingleResult();
	      entityManager.getTransaction().commit();
	      if(keydata!= null){
	    	  return keydata;
	      }
	  }catch(Exception error){
		error.printStackTrace();
		 if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
	  }
	  return keydata; 
	}
	
	@PersistenceContext
	public Result UpdateSeqNo(KeyData keydata,EntityManager entityManager){
		Result res = new Result();
		try{
			entityManager.getTransaction().begin( );
			keydata.setSequenceNo(keydata.getSequenceNo()+1);
			if(entityManager.merge(keydata) != null){
				res.setState(true);
			    res.setMsgCode("0000");
			    res.setMsgDesc("Success");
			}else{
				res.setState(false);
			    res.setMsgCode("0014");
			    res.setMsgDesc("Failed");
			}
			entityManager.getTransaction().commit();
		}catch(Exception error){
			error.printStackTrace();
			 if(entityManager.getTransaction() != null) entityManager.getTransaction().rollback(); entityManager.close();
		 }
		return res;
	}
}
