package org.it.discovery.training.hibernate.bootstrap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.it.discovery.training.hibernate.model.Book;
import org.it.discovery.training.hibernate.model.Person;
import org.it.discovery.training.hibernate.util.HibernateUtil;

public class HibernateStarter {

	public static void main(String[] args) {
		Session session = null;
		try (SessionFactory factory = HibernateUtil.getSessionFactory()) {

			session = factory.getCurrentSession();
			session.beginTransaction();
			// Persistent operations
			Person person = new Person();
			person.setName("Gaving King");
			Book book = new Book();
			book.setName("Hibernate 3");
			book.setAuthor(person);
			session.persist(book);
			System.out.println(book.getId());
			session.getTransaction().commit();
			session = factory.getCurrentSession();
			session.beginTransaction();
			Book book2 = session.load(Book.class, book.getId());
			book2.setName("Gavin King++");
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			if (session != null) {
				session.getTransaction().rollback();
			}
		}

	}

}
