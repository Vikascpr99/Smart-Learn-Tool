package com.smartlearntool.SmartLearnTool.dtos;

import com.smartlearntool.SmartLearnTool.entities.Category;
import com.smartlearntool.SmartLearnTool.entities.Video;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto
{

    private String id;

    @NotEmpty(message = "Title is Mandatory")
    @Size(min = 3, max = 10, message = "Size should be in between 3 to 10")
    private String title;

    private String shortDesc;

    private String longDesc;

    private double price;

    private boolean live = false;

    private double discount;

    private Date createdDate;

    private String banner;

    private List<VideoDto> videos = new ArrayList<>();


    private List<CategoryDto> categoryList = new ArrayList<>();
}
