/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Student;
import entity.Users;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.SessionUtil;

/**
 *
 * @author tofail
 */
public class RegisterDao {
    public boolean register(Users u){
        try {
            SessionFactory factory= HibernateUtil.getSessionFactory();
            Session session =factory.openSession();
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
            session.close();
             return true;
            
        } catch (Exception e) {
        }
        return false;
        
    }
    public boolean login(Users user){
        try {
            SessionFactory factory= HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            Query query= session.createQuery("select u from Users u where u.username=:username and u.password=:password");
            query.setString("username", user.getUsername());
            query.setString("password", user.getPassword());
            List<Users>cList=query.list();
            cList.toString();
            session.getTransaction().commit();
            session.close();
            if(cList.size()>0){
                HttpSession session1 = SessionUtil.getSession();
                session1.setAttribute("username", cList.get(0).getUsername());
                session1.setAttribute("id", cList.get(0).getId()); 
                return true;
            }else
                return false;
            
        } catch (Exception e) {
        }
        return false;
        
    }
     public boolean addusers(Users users) {
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
       public List<Users> allUsers() {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        List<Users> sList = session.createQuery("select al from Users al").list();
        sList.toString();
        session.close();
        return sList;
    }
        public boolean editUsers(Users users) {

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            //Query q = (Query) session.createQuery("from Users ");
             System.out.println("dddddddddddddd..."+ users.getUsername());
            Users u = (Users)session.get(Users.class, users.getId());
            u.setId(users.getId());
            u.setUsername(users.getUsername());
            u.setEmail(users.getEmail());
            session.update(u);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        public boolean delete(Users users) {
        //Student st= new Student();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        Users usrs = (Users) session.get(Users.class, users.getId());

        session.delete(usrs);
        session.getTransaction().commit();
        session.close();
        return true;
    }
         public List searchResult(Users users) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Users where id=? , username=? and email=?");
        q.setParameter(0, users.getId());
        q.setParameter(1, users.getUsername());
        q.setParameter(2, users.getEmail());
        List<Users> searchList = q.list();
       
        session.close();
        return searchList;

    }
         
         public List searchResultbyId(Users users) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Users where id=?");
        q.setParameter(0, users.getId());
        List<Users> searchList = q.list();
       
        session.close();
        return searchList;

    }
//          public boolean editUsers(Users users) {
//
//        try {
//            SessionFactory factory = HibernateUtil.getSessionFactory();
//            Session session = factory.openSession();
//            session.beginTransaction();
//            Query q = (Query) session.createQuery("from Users");
//            session.update(users);
//            session.getTransaction().commit();
//            session.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
  public List searchRsbyId(Users users) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Users where id=?,username=?,fullname=?,email=?,password=? and regdate=?");
        q.setParameter(0, users.getId());
        q.setParameter(1, users.getUsername());
        q.setParameter(2, users.getFullname());
        q.setParameter(3, users.getEmail());
        q.setParameter(4, users.getPassword());
        q.setParameter(5, users.getRegdate());
        List<Users> searchList = q.list();
       
        session.close();
        return searchList;

    }
    public boolean searchUsers(Users users) {

        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            session.beginTransaction();
            //Query q = (Query) session.createQuery("from Users ");
             System.out.println("dddddddddddddd..."+ users.getUsername());
            Users u = (Users)session.get(Users.class, users.getId());
            u.setId(users.getId());
            u.setUsername(users.getUsername());
            u.setFullname(users.getFullname());
            u.setEmail(users.getEmail());
            u.setPassword(users.getPassword());
            u.setRegdate(users.getRegdate());
            
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     public List searchRs(Users users) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Query q = session.createQuery("from Users where id=?");
        q.setParameter(0, users.getId());
        q.setParameter(1, users.getUsername());
        q.setParameter(2, users.getFullname());
        q.setParameter(3, users.getEmail());
        q.setParameter(4, users.getPassword());
        q.setParameter(5, users.getRegdate());
        List<Users> searchList = q.list();
       
        session.close();
        return searchList;

    }
    
}
