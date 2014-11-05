package br.unb.cic.iris.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration();  
	        configuration.configure();  
	         
	        Properties properties = configuration.getProperties();
	         
	        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
	        return configuration.buildSessionFactory(serviceRegistry); 
		}
		catch(Throwable t) {
			throw new ExceptionInInitializerError(t);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
