package com.nirvasoft.fi.mgr;

import javax.persistence.EntityManager;

import com.nirvasoft.fi.dao.KeyGenerateDao;
import com.nirvasoft.fi.framework.Result;
import com.nirvasoft.fi.shared.KeyData;

public class KeyGenerateMgr {
	public static Result getKeyDataMgr(String objectName, int datatype,EntityManager entityManager){
		Result res = new Result();
	    KeyData keyData = new KeyData();
	    KeyGenerateDao keydao = new KeyGenerateDao();
	    String data = "";
	    try{
	    	keyData = keydao.getKeyData(objectName, datatype, entityManager);
	    	if(keyData.getKeyTableSysKey()!= 0L){
	    		if (datatype == 1) {
	    	          data = leadZeros( String.valueOf(keyData.getSequenceNo()), keyData.getNoOfDigit(), keyData.getPrefix());
	    	    }else if (datatype == 2) {
	    	          data = String.valueOf(keyData.getSequenceNo());
	    	    }
	    		res.setKeyString(data);
	            Result result = new Result();
	            result = keydao.UpdateSeqNo(keyData, entityManager);
	            res.setMsgCode(result.getMsgCode());
	            res.setMsgDesc(result.getMsgDesc());
	    	}else {
	            res.setMsgCode("0014");
	            res.setMsgDesc("There is no record about " + objectName + "  in the table.");
	          }
	    } catch (Exception e) {
	        res.setMsgCode("0014");
	        res.setMsgDesc(e.getMessage());
	    }
	    return res;
	}
	
	public static String leadZeros(String p, int size, String laeadChar) {
	    String ret = p;
	    for (int i = p.length(); i < size; i++) {
	      ret = "0" + ret;
	    }
	    ret = laeadChar + ret;
	    return ret;
	}
}
