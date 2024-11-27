package com.smartlearntool.SmartLearnTool.Services;

import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CourseService
{

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(String id, CourseDto courseDto);

    CourseDto getCoursrById(String id);

    Page<CourseDto> getAllCourses(Pageable pageable);


    void deleteCourse(String id);

    List<CourseDto> searchCourse(String keyword);
}
