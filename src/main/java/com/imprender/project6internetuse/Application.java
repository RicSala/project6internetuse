package com.imprender.project6internetuse;

import com.imprender.project6internetuse.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class Application {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }


    public static void main(String[] args) {


    }

    private static Country findCountryById(int id) {

        //Open session
        Session session = sessionFactory.openSession();

        //Retrieve persistent object
        Country country = session.get(Country.class, id);

        //Close session
        session.close();

        return country;
    }

    private static void update(Country country) {
        Session session = sessionFactory.openSession();

        //Begin the transaction
        session.beginTransaction();

        //update
        session.update(country);

        //commit the transaction
        session.getTransaction().commit();

        session.close();
    }

    private static List<Country> fetchAllCountries() {
        Session session = sessionFactory.openSession();

        List<Country> countries;

//        Create a criteria builder
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

//        Create criteria query
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);

        //Specify a criteria root
        criteriaQuery.from(Country.class);

        //Execute query
        countries = session.createQuery(criteriaQuery).getResultList();

        session.close();

        return countries;
    }

    private static int save(Country country) {
        //        Open a session

        Session session = sessionFactory.openSession();

//        Begin a transaction
        Transaction transaction = session.beginTransaction();

//        use the session to save the country
        //need a cast because returns a serializable object
        int id = (int) session.save(country);

//        commit the transaction
        transaction.commit();

//        close the session
        session.close();

        return id;
    }

    private static void delete(Country country) {
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        session.delete(country);

        transaction.commit();

        session.close();
    }
}
