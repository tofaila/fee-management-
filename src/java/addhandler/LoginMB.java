/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addhandler;

import static com.mchange.v2.c3p0.impl.C3P0Defaults.user;
import dao.RegisterDao;
import entity.Users;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.SessionUtil;

@ManagedBean
@SessionScoped
public class LoginMB {
    Users users = new Users();

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
    public String login(){
        users.setUsername(users.getUsername());
        users.setPassword(users.getPassword());
        if(new RegisterDao().login(users)){
            System.out.println("Login success");
//            try {
//                ExternalContext ec= SessionUtil.getExternalContext();
//                HttpSession session= SessionUtil.getSession();
//                String redirect = ec.getRequestContextPath() + "welcome.xhtml"; 
//                if(!session.getAttribute("redirect").toString().equals("")){
//                    redirect = session.getAttribute("redirect").toString();
//                }
//                 ec.redirect(redirect);
//            } catch (Exception e) {
//            }
            
          return "welcome";  
            
        }else{
             System.out.println("Login failed");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Login failed Please register before login",""));
                    }
        return null;
    }
    
    
    
}
