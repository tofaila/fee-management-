/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhandler;
import dao.ListDao;
import dao.RegisterDao;
import entity.Student;
import entity.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

@ManagedBean
@SessionScoped
public class StudentMB implements Serializable {
    Student st= new Student();
    List<Student>uList= new ArrayList<>();

    public List<Student> getuList() {
        return uList;
    }

    public void setuList(List<Student> uList) {
        this.uList = uList;
    }

   
    

    public Student getSt() {
        return st;
    }

    public void setSt(Student st) {
        this.st = st;
    }
   
    public List<Student> getAllStudent(){
    List<Student> sList = new ListDao().allStudent();
    return sList;
    }
   public List<Student> addStudent(){
       System.out.println("adding .....");
       st.setId(st.getId());
       st.setRollno(st.getRollno());
       st.setName(st.getName());
       st.setEmail(st.getEmail());
       st.setPassword(st.getPassword());
       st.setSex(st.getSex());
       st.setCourse(st.getCourse());
       st.setFee(st.getFee());
       st.setPaid(st.getPaid());
       st.setDue(st.getDue());
       st.setAddress(st.getAddress());
       st.setContact(st.getContact());
       if(new ListDao().addstudent(st)){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Saved",""));
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Data not Saved",""));
       }
       return null;
   }
   public List<Student> getAllSt(){
       List<Student>sList=new ListDao().getAllStudent();
       return sList;
       
   }
//   public String updateStuent(int id){
//       System.out.println("updating......");
//       st.setId(st.getId());
//       st.setName(st.getName());
//       st.setEmail(st.getEmail());
////       if(new ListDao().updateStuent(st)){
////           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Saved",""));
////                   
////       }else{
////           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Data Not Saved",""));
////       } 
//
//              SessionFactory factory= HibernateUtil.getSessionFactory();
//              Session session = factory.openSession();
//              Query query = session.createQuery("update Student set id=:stdid");
//              query.setParameter("stdid", id);
//              session.beginTransaction();
//              session.update(st);
//              session.getTransaction().commit();
//              session.close();


//       return null;
//   }
   public String editSt(int id){
       System.out.println("updating...");   
       st.setId(st.getId());
       st.setName(st.getName());
       st.setEmail(st.getEmail());
       if(new ListDao().editStudent(st)){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data Updated",""));
       }else{
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Data Not Updated",""));
       }
       return null;
   }
 public List<Student>doDelete(Student st){
     if(new ListDao().delete(st)){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Data deleted",""));
     }else{
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Data Not deleted",""));
     }
     return null;
     
  }
  public String searchStudent() {
        uList = new ListDao().searchResult(st);

        return null;
    }

   public String editStudent(int id) {
        System.out.println("updating...");
        st.setId(st.getId());
        st.setName(st.getName());
        st.setEmail(st.getEmail());
        if (new ListDao().editStudent(st)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not Updated", ""));
        }
        return null;
    }
     public String editUsrs() {
        System.out.println("updating..."+ st.getName());
        
        if (new ListDao().editStudent(st)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not Updated", ""));
        }
        return "displaystudent";
    }
     public String searchUserbyId(Student std) {
        
        uList = new ListDao().searchResultbyId(std);
        System.out.println(uList.size());
        for (int i = 0; i < uList.size(); i++) {
            st.setId(uList.get(i).getId());
            st.setName(uList.get(i).getName());
            st.setEmail(uList.get(i).getEmail());

        }
        return "edit_stu";
    }
     public String searchSt(){
         uList= new ListDao().searchStudent(st);
         return "displaystudent";
     }
    
    
}

