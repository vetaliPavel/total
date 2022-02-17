package org.example.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.commons.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.example.entities.Post;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;


public class Auto {

    @Property
    private List<Post> allPost;
    @Property
    private Post post;
    @Inject
    private Messages messages;


    @Inject
    private Session session;
    public void onActivate(){

    }
    public void setupRender(){
//        allPost = session.createCriteria(Post.class).list();
//        String  sql ="select * from post where id = :post_id";
//        SQLQuery query = session.createSQLQuery(sql);
//        query.addEntity(Post.class);
//        query.setParameter("post_id",2);
//        allPost = query.list();
        Criteria criteria = session.createCriteria(Post.class);
       // criteria.add(Restrictions.eq("id",3L));
        allPost = criteria.list();
    }

    public Messages getMessages(){
        return messages;
    }
    public void setMessages(Messages messages){
        this.messages = messages;
    }
}

