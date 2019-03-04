package ProjektPZ;

import ProjektPZ.dialogs.ErrorDialog;
import ProjektPZ.pattern.Pattern;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.LinkedList;
import java.util.List;

public class Database {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Session Factory could not be created." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void deletePattern(String patternName) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.delete(session.createQuery("FROM Pattern P WHERE P.weaponName = '" + patternName + "'").list().get(0));
            transaction.commit();
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void savePattern(Pattern pattern) {
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        try {
            session.save(pattern);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List updateList() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<String> patterns = new LinkedList<>();

        try {
            patterns = session.createQuery("SELECT weaponName FROM Pattern").list();
            transaction.commit();
        } catch (Exception ex) {
            new ErrorDialog(ex.getMessage());
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }

        return patterns;

    }

    public Pattern loadPattern(String patternName) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Pattern pattern = new Pattern();
        try {
            pattern = (Pattern) session.createQuery("FROM Pattern P WHERE P.weaponName = '" + patternName + "'").list().get(0);
            transaction.commit();
        } catch (Exception e) {
            new ErrorDialog(e.getMessage());
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }

        return pattern;
    }

    public void close() {
        sessionFactory.close();
    }
}
