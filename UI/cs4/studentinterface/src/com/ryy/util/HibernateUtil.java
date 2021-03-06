package com.ryy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
/**
 * 
* <p>Title: HibernateUtil</p>
* <p>Description: 获取Session</p>
* @author renyuyuan
* @date 2018年12月11日
 */
public class HibernateUtil {
	/**
	 * sessionfactory工厂用于获取session
	 */
	private static SessionFactory sessionFactory;
	/**
	 * 构造方法私有化
	 */
	private HibernateUtil() {}
	/**
	 * 
	 * <p>Title: init</p>
	 * <p>Description: 初始化sessionfactory</p>
	 */
	private static void init() {
		if(sessionFactory==null) {
			StandardServiceRegistry  serviceRegistry=new StandardServiceRegistryBuilder().configure().build(); 
	        sessionFactory=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
		}
	}
	public static Session getSession() {
		if(sessionFactory==null) {
			init();
		}
		return sessionFactory.openSession();
	}
}
