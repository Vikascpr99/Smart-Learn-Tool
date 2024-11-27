package com.smartlearntool.SmartLearnTool.Controllers;

import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import com.smartlearntool.SmartLearnTool.Services.CourseService;
import com.smartlearntool.SmartLearnTool.dtos.CategoryDto;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {



    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    //    Create Category

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.createCourse(courseDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> updateCourse(@PathVariable String id, @RequestBody CourseDto courseDto){
        return ResponseEntity.ok(courseService.updateCourse(id, courseDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable String id){
        return ResponseEntity.ok(courseService.getCoursrById(id));
    }
    @GetMapping
    public ResponseEntity<Page<CourseDto>> getAllCourses(Pageable pageable){
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
