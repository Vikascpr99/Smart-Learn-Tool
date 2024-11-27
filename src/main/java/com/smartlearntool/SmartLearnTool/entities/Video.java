package com.smartlearntool.SmartLearnTool.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    private String videoId;
    private String title;
    private String shortDesc;
    private String filePath;
    private String contentType;

    @ManyToOne
    private Course course;



}
