package com.smartlearntool.SmartLearnTool.dtos;

import com.smartlearntool.SmartLearnTool.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto
{
    private String videoId;

    private String title;

    private String shortDesc;

    private String filePath;

    private String contentType;

    private Course course;
}
