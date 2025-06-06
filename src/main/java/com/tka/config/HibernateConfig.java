package com.tka.config;

import org.hibernate.cfg.Configuration;

import org.hibernate.SessionFactory;
import com.tka.entity.BankAccount;

public class HibernateConfig {
	public static SessionFactory getSessionFactory() {
		Configuration cfg = new Configuration().configure();

		cfg.configure("hibernate.cfg.xml");
		cfg.addAnnotatedClass(BankAccount.class);
		SessionFactory sf = cfg.buildSessionFactory();

		return sf;
	}
}
