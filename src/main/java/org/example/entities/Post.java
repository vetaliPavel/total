package org.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {
    @Id
    private  Long id;
    private String title;
    private String subTitle;
    private String linkTitle;
    private String timeShow;
    private String description;
    private String pathImages;
    private String address;

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSubTitle (){
        return subTitle;
    }
    public void setSubTitle(String subTitle){
        this.subTitle = subTitle;
    }
    public String getLinkTitle() {
        return linkTitle;
    }
    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }
    public String getTimeShow() {
        return timeShow;
    }
    public void setTimeShow(String timeShow) {
        this.timeShow = timeShow;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPathImages() {
        return pathImages;
    }
    public void setPathImages(String pathImages) {
        this.pathImages = pathImages;
    }


    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
}

