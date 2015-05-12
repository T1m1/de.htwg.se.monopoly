package de.htwg.monopoly.persistence.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import de.htwg.monopoly.context.IMonopolyGame;
import de.htwg.monopoly.persistence.hibernate.PersistentGame;

public class HibernateUtil {

	private static final SessionFactory SESSIONFACTORY;

	static {
		final AnnotationConfiguration cfg = new AnnotationConfiguration();
		cfg.configure("/hibernate.cfg.xml");
		SESSIONFACTORY = cfg.buildSessionFactory();
	}

	private HibernateUtil() {

	}

	public static SessionFactory getInstance() {
		return SESSIONFACTORY;
	}

	public static PersistentGame transformToHibernate(IMonopolyGame game) {
		return null;
	}

	
	public static IMonopolyGame transformFromHibernate(PersistentGame game) {
		return null;
	}
}
