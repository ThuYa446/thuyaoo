package com.nirvasoft.fi.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.boot.Metadata;
//import org.hibernate.boot.MetadataSources;
//import org.hibernate.boot.registry.StandardServiceRegistry;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class hibernateUtils {

//	public static SessionFactory openFactory(){
//		SessionFactory factory= null;
//		try{
//			StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
//		    Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
//		    factory = meta.getSessionFactoryBuilder().build(); 
//		}catch(Exception e){
//			e.getMessage();
//		}
//		return factory;
//	}
//	public static Session openSession() throws HibernateException{
//		Session session = null;
//		try{
//		     session = openFactory().openSession();
//		}catch(Exception e){
//			e.getMessage();
//		}
//	      return session;
//	}
//	
//	public static Transaction beginTransaction(){
//		Transaction transaction = null;
//		try{
//			Session session = openSession();
//			transaction = session.beginTransaction(); 
//		}catch(Exception e){
//			e.getMessage();
//		}
//	      return transaction;
//	}

	public static EntityManager getEntityManager(){
		EntityManager entityManager = null;
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("TaxPayerService");
		entityManager = emfactory.createEntityManager( );
		return entityManager;
	}
}
