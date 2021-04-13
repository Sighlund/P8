package yolo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

// Søren har skrevet noget fed kode
public class App {
    public static void main(String[] args){

        Descriptor MadEksempel = new Descriptor();
        MadEksempel.setName("Eksempel");
        MadEksempel.setSupplier("Firma");
        MadEksempel.setItemNumber(250);
        MadEksempel.setPrimaryCategory("Kød");
        MadEksempel.setSecondaryCategory("Gaming");
        MadEksempel.setConcitoId(4);

        System.out.println("Trying to create a test connection");
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Descriptor.class);
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sf = configuration.buildSessionFactory(ssrb.build());

        Session session = sf.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(MadEksempel);

        transaction.commit();

        session.save(MadEksempel);



        System.out.println("It worked! Very nice))");

    }
}
