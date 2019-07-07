/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Student;
import entity.Users;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import static util.HibernateUtil.getSessionFactory;
import util.SessionUtil;

/**
 *
 * @author tofail
 */
public class ListDao {

    public List stList() {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Student> cList
                    = session.createQuery("SELECT a1.name FROM Student a1 ")
                            .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<Student> stListByName(String name) {

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List<Student> cList
                    = session.createQuery("SELECT a1 FROM Student a1"
                            + " WHERE lower(name) = '" + name.toLowerCase() + "'")
                            .list();
            cList.toString();
            session.close();
            return cList;
        } catch (Exception e) {

        }
        return null;

    }

    public List<Student> allStudent() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List<Student> sList = session.createQuery("select al from Student al").list();
        sList.toString();
        session.close();
        return sList;
    }

    public boolean addstudent(Student st) {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(st);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public List<Student> getAllStudent() {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        Query query = session.createQuery("from Student");
        List<Student> sList = query.list();
        sList.toString();
        session.close();
        return sList;
    }
//      public boolean updateStuent(Student st){
//          try {
//              SessionFactory factory= HibernateUtil.getSessionFactory();
//              Session session = factory.openSession();
//              session.beginTransaction();
//              session.update(st);
//              session.getTransaction().commit();
//              session.close();
//              return true;
//          } catch (Exception e) {
//              return false;
//          }
//      }

//    public boolean editStudent(Student st) {
//
//        try {
//            SessionFactory factory = HibernateUtil.getSessionFactory();
//            Session session = factory.openSession();
//            session.beginTransaction();
//            Query q = (Query) session.createQuery("from Student");
//            session.update(st);
//            session.getTransaction().commit();
//            session.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    public boolean delete(Student st) {
        //Student st= new Student();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Student std = (Student) session.get(Student.class, st.getId());

        session.delete(std);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public List searchResult(Student st) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Student where id=? , name=? and email=?");
        q.setParameter(0, st.getId());
        q.setParameter(1, st.getName());
        q.setParameter(2, st.getEmail());
        List<Student> searchList = q.list();
       
        session.close();
        return searchList;

    }
      public List searchResultbyId(Student st) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Student where id=?");
        q.setParameter(0, st.getId());
        List<Student> searchList = q.list();
       
        session.close();
        return searchList;

    }
      
        public boolean editStudent(Student st) {

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            //Query q = (Query) session.createQuery("from Users ");
             System.out.println("dddddddddddddd..."+ st.getName());
            Student s = (Student)session.get(Student.class, st.getId());
            s.setId(st.getId());
            s.setName(st.getName());
            s.setEmail(st.getEmail());
            session.update(s);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     public List searchStudent(Student st){
       SessionFactory sf=HibernateUtil.getSessionFactory();
       Session session=sf.openSession();
       Query q= session.createQuery("from Student where rollno=?");
       q.setParameter(0, st.getRollno());
       List<Student> searchList=q.list();
       session.close();
       return searchList;
     }
}
