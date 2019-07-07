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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import util.SessionUtil;

@ManagedBean
@SessionScoped
public class RegisterMB {

    Users users = new Users();
    //  Users selectedUsers; 
    List<Users> uList = new ArrayList<>();

    public List<Users> getuList() {
        return uList;
    }

    public void setuList(List<Users> uList) {
        this.uList = uList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

//    public Users getSelectedUsers() {
//        return selectedUsers;
//    }
//
//    public void setSelectedUsers(Users selectedUsers) {
//        this.selectedUsers = selectedUsers;
//    }
    public String register() {
        users.setUsername(users.getUsername());
        users.setFullname(users.getFullname());
        users.setEmail(users.getEmail());
        users.setPassword(users.getPassword());
        users.setRegdate(new Date());
//       boolean s = new RegisterDao().register(users);
//       if(s){
//       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registration successful",""));
//       }
//       else{ FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"something went wrong",""));}
        if (new RegisterDao().register(users)) {
            try {
                ExternalContext ec = SessionUtil.getExternalContext();
                HttpSession session = SessionUtil.getSession();
                String redirect = ec.getRequestContextPath() + "/index.xhtml";
                ec.redirect(redirect);
            } catch (Exception e) {
            }
//           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Registration successful",""));
        } else {
//           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"something went wrong",""));
//       }

        }
        return null;

    }

    public List<Users> getAllUsers() {
        List<Users> sList = new RegisterDao().allUsers();
        return sList;
    }

    public String editUsers(int id) {
        System.out.println("updating...");
        users.setId(users.getId());
        users.setUsername(users.getUsername());
        users.setEmail(users.getEmail());
        if (new RegisterDao().editUsers(users)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not Updated", ""));
        }
        return null;
    }

    public List<Users> doDelete(Users users) {
        if (new RegisterDao().delete(users)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data deleted", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not deleted", ""));
        }
        return null;

    }

    public String searchUser() {
        uList = new RegisterDao().searchResult(users);

        return null;
    }

    public List<Student> addUsers() {
        System.out.println("adding .....");
        users.setId(users.getId());
        users.setUsername(users.getUsername());
        users.setFullname(users.getFullname());
        users.setEmail(users.getEmail());
        users.setPassword(users.getPassword());
        users.setRegdate(users.getRegdate());

        if (new RegisterDao().addusers(users)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data not Saved", ""));
        }
        return null;
    }

    public String editUsrs() {
        System.out.println("updating..."+ users.getUsername());
        
        if (new RegisterDao().editUsers(users)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not Updated", ""));
        }
        return "displayaccountant";
    }

    public String searchUserbyId(Users user) {
        
        uList = new RegisterDao().searchResultbyId(user);
        System.out.println(uList.size());
        for (int i = 0; i < uList.size(); i++) {
            users.setId(uList.get(i).getId());
            users.setUsername(uList.get(i).getUsername());
            users.setEmail(uList.get(i).getEmail());

        }
        return "edit_accountant";
    }
     public String searchAccbyId(Users user) {
        
        uList = new RegisterDao().searchRsbyId(user);
        System.out.println(uList.size());
        for (int i = 0; i < uList.size(); i++) {
            users.setId(uList.get(i).getId());
            users.setUsername(uList.get(i).getUsername());
            users.setFullname(uList.get(i).getFullname());
            users.setEmail(uList.get(i).getEmail());
            users.setPassword(uList.get(i).getPassword());
            users.setRegdate(uList.get(i).getRegdate());

        }
        return "search_acc";
    }
      public String searchUsrs() {
        System.out.println("searching..."+ users.getUsername());
        
        if (new RegisterDao().searchUsers(users)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Updated", ""));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Data Not Updated", ""));
        }
        return "displayaccountant";
    }
       public String searchUer() {
        uList = new RegisterDao().searchRs(users);

        return null;
    }
}
