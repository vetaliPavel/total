package org.example.pages;


import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.example.entities.Post;
import org.example.entities.Users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.regex.Pattern;

public class Signup {

    @Persist
    @Property
    private Users user;
    @Inject
    private Messages messages;
    @Component(id = "signupForm")
    private Form signupForm;
    @Component(id = "username")
    private TextField userNameTextField;
    @Component(id = "lastname")
    private TextField lastNameTextField;
    @Inject
    private Session session;
    @Inject
    private PageRenderLinkSource linkSource;

    public Messages getMessages() {
        return messages;
    }
    public void setMessages(Messages messages) {
        this.messages = messages;
    }
    public void onSuccessFromSignupForm() {
        saveUser(user);
    }
    public TextField getUserNameTextField() {
        return userNameTextField;
    }
    public void setUserNameTextField(TextField userNameTextField) {
        this.userNameTextField = userNameTextField;
    }
    public TextField getLastNameTextField() {
        return lastNameTextField;
    }
    public void setLastNameTextField(TextField lastNameTextField) {
        this.lastNameTextField = lastNameTextField;
    }

    public void onActivate() {
    }
    public void setupRender() {
        user = new Users();
    }
    // запрос к базе данных о всех юзерах
    public List<Users> getUsers() {
        Criteria criteria = session.createCriteria(Users.class);
        List<Users> userAll = criteria.list();
        return userAll;
    }
    // запрос к базе данных о поля "username"
    public List<Users> getUserByUsername(String userName) {
        List<Users> userAll = null;
        Criteria criteria = session.createCriteria(Users.class);
        criteria.add(Restrictions.like("username", userName));
        userAll = criteria.list();
        return userAll;
    }


    @CommitAfter
    private void saveUser(Users user) {
        if (user != null && user.getUsername() != null) {
            session.persist(user);
        }
    }
    public void onValidateFromSignupForm() {
        if (user != null && user.getPassword() == null) {
            signupForm.recordError("Please provide a value for Password");

        }
        if(user != null && user.getPassword() != null && !isPasswordValid(user.getPassword())){
            signupForm.recordError("Password must contain at least one number");
        }
        if (user != null && getUserByUsername(user.getUsername()) != null && !getUserByUsername(user.getUsername()).isEmpty()) {
            signupForm.recordError(getUserNameTextField(), "Such username already exists");
        }
        if (user != null && user.getLastName() != null && user.getLastName().length() > 10) {
            signupForm.recordError(getLastNameTextField(), "Text must not exceed 10 characters");
        }
    }
    public boolean isPasswordValid(String input){
        String regex = ".*\\d.*";
      return  Pattern.matches(regex, input);

    }
    public String getLoginPage(){
     //   return linkSource.createPageRenderLink(Login.class).toString();
         return "login";
    }

}

////        allPost = session.createCriteria(Post.class).list();
////        String  sql ="select * from post where id = :post_id";
////        SQLQuery query = session.createSQLQuery(sql);
////        query.addEntity(Post.class);
////        query.setParameter("post_id",2);
////        allPost = query.list();