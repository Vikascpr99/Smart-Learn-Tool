package com.smartlearntool.SmartLearnTool.entities;

import com.smartlearntool.SmartLearnTool.dtos.VideoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    private String id;
    private String title;
    private String shortDesc;
    @Column(length = 2000)
    private String longDesc;
    private double price;
    private boolean live = false;
    private double discount;
    private Date createdDate;

    private String banner;
    private String bannerContentType;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<Video> videos = new ArrayList<>();
//    public void addVideo(Video video){
//        videos.add(video);
//        video.setCourse(this);
//    }
//    public void removeVideo (Video video){
//        videos.remove(video);
//        video.setCourse(null);
//    }

    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category){
        categoryList.add(category);
        category.getCourses().add(this);
    }

    public void removeCategory (Category category){
        categoryList.remove(category);
        category.getCourses().remove(this);
    }



}
