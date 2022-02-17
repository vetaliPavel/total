package org.example.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.http.Link;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.example.entities.Users;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class Login {

  @Persist
  @Property
  private Users user;
  @Inject
  private Messages messages;

  @Component(id = "loginForm")
  private Form loginForm;

  @Component(id = "username")
  private TextField UserNameTextField;

  @Component(id = "password")
  private TextField PasswordTextField;

  @Inject
  private PageRenderLinkSource linkSource;

  @Inject
  private Session session;


  public Messages getMessages() {
    return messages;
  }

  public void setMessages(Messages messages) {
    this.messages = messages;
  }

  public TextField getUserNameTextField() {
    return UserNameTextField;
  }

  public void setUserNameTextField(TextField userNameTextField) {
    UserNameTextField = userNameTextField;
  }

  public TextField getPasswordTextField() {
    return PasswordTextField;
  }

  public void setPasswordTextField(TextField passwordTextField) {
    PasswordTextField = passwordTextField;
  }

  public void onActivate() {
  }

  public void setupRender() {
    user = new Users();

  }


  public List<Users> getUserByUsername(String userName, String password) {
    List<Users> userAll = null;
    Criteria criteria = session.createCriteria(Users.class);
    criteria.add(Restrictions.like("username", userName));
    criteria.add(Restrictions.like("password", password));
    userAll = criteria.list();
    return userAll;
  }

  public void onValidateFromLoginForm() {
    if (user != null && getUserByUsername(user.getUsername(),user.getPassword()) != null && getUserByUsername(user.getUsername(),user.getPassword()).isEmpty()) {
      loginForm.recordError( getMessages().get("error.loginForm"));
    }
  }
  public Link onSuccessFromLoginForm() {
  return linkSource.createPageRenderLink(Auto.class);
  }
  public String getSignupPage(){
   // return linkSource.createPageRenderLink(Signup.class).toString();
    return "signup";
  }
}