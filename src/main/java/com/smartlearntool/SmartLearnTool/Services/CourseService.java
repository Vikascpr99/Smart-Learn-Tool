package com.smartlearntool.SmartLearnTool.Services;

import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.ResourceContentType;
import com.smartlearntool.SmartLearnTool.entities.Course;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface CourseService
{

    CourseDto createCourse(CourseDto courseDto);

    CourseDto updateCourse(String id, CourseDto courseDto);

    CourseDto getCoursrById(String id);

    Page<CourseDto> getAllCourses(Pageable pageable);


    void deleteCourse(String id);

    List<CourseDto> searchCourse(String keyword);
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;

    ResourceContentType getCourseBannerById(String courseId);
}
