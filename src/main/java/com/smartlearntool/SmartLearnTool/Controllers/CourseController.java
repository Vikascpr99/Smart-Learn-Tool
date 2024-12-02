package com.smartlearntool.SmartLearnTool.Controllers;

import com.smartlearntool.SmartLearnTool.Config.AppConstant;
import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import com.smartlearntool.SmartLearnTool.Services.CourseService;
import com.smartlearntool.SmartLearnTool.Services.FileService;
import com.smartlearntool.SmartLearnTool.dtos.CategoryDto;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.CustomMessage;
import com.smartlearntool.SmartLearnTool.dtos.ResourceContentType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @PostMapping("/{courseId}/banners")
    public ResponseEntity<?> uploadBanner(
            @PathVariable String courseId,
            @RequestParam("banner")MultipartFile banner
            ) throws IOException {
        String contentType = banner.getContentType();
        if (contentType== null){
            contentType = "image/png";

        }
        else  if (contentType.equalsIgnoreCase("image/png") || contentType.equalsIgnoreCase("image/jpg") || contentType.equalsIgnoreCase("image/jpeg")){

        }
        else{
            CustomMessage customMessage = new CustomMessage();
            customMessage.setSuccess(false);
            customMessage.setMessage("Invalid File Format");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);

            }
        CourseDto courseDto = courseService.saveBanner(banner, courseId);
        return ResponseEntity.ok(courseDto);
    }

    @GetMapping("/{courseId}/banners")
    public ResponseEntity<Resource> serveBanner(
            @PathVariable String courseId
    ){
        ResourceContentType resourceContentType = courseService.getCourseBannerById(courseId);
       return  ResponseEntity
               .ok()
               .contentType(MediaType.parseMediaType(resourceContentType.getContentType()))
               .body(resourceContentType.getResource());
    }

}
