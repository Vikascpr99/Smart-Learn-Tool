package com.smartlearntool.SmartLearnTool.dtos;

import com.smartlearntool.SmartLearnTool.entities.Course;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
public class CategoryDto
{


    private String id;

    @NotEmpty(message = "title is required !!!")
    @Size(min = 2, max = 20, message = "title must be of 2 to 20 character")
    private String title;

    @NotEmpty(message = "Description should not empty")
    private String desc;

    private Date addedDate;


//    private List<Course> courses = new ArrayList<>();
}
