package com.uttara.hib;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestHib {

	public static void main(String[] args) {
		
		Address a = new Address("No 10","1st Block Rajajinagar","Bangalore","560010");
		PhoneNumber p1 = new PhoneNumber("080", "+91", "9980403991");
		PhoneNumber p2 = new PhoneNumber("080", "+91", "08023528662");
		Employee e = new Employee("Rasimmaraju",new Date(),a);
		e.getEmails().add("emp1@gmail.com");
		e.getEmails().add("1emp@gmail.com");
		e.getEmails().add("1emp1@gmail.com");
		e.getEmails().add("11emp@gmail.com");
		e.getEmails().add("emp11@gmail.com");
		e.getPhNums().add(p1);
		e.getPhNums().add(p2);
		
		Employee e2 = new Employee("Katrina",new Date(),new Address("No.1","Juhu Beach","Mumbai","600001"));
		e2.getEmails().add("kat1@gmail.com");
		e2.getEmails().add("kat2@gmail.com");
		e2.getEmails().add("kat3@gmail.com");
		e2.getPhNums().add(new PhoneNumber("061","+91","9989999999"));
		e2.getPhNums().add(new PhoneNumber("061","+91","9989999998"));
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		
		Session s = sf.openSession();
		s.beginTransaction();
		
		s.save(e);
		s.save(e2);
		
		s.getTransaction().commit();
		
		s.close();
		
		Session s2 = sf.openSession();
		s2.beginTransaction();
		
		List<Employee> emps = s2.createQuery("from Employee").getResultList();
		System.out.println(emps);
		
		s2.getTransaction().commit();
		s2.close();
		
		HibernateUtil.getSessionFactory().close();
		

	}

}
