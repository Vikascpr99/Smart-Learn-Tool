package com.smartlearntool.SmartLearnTool.Services.Implementation;

import com.smartlearntool.SmartLearnTool.Config.AppConstant;
import com.smartlearntool.SmartLearnTool.Repositories.CourseRepo;
import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import com.smartlearntool.SmartLearnTool.Services.CourseService;
import com.smartlearntool.SmartLearnTool.Services.FileService;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.ResourceContentType;
import com.smartlearntool.SmartLearnTool.entities.Course;
import com.smartlearntool.SmartLearnTool.exceptions.ResourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService

{

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper, CategoryService categoryService) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    private CourseRepo courseRepo;
    private ModelMapper modelMapper;
    private CategoryService categoryService;

    @Autowired
    private FileService fileService;
    


//    public CourseServiceImpl(CourseRepo courseRepo) {
//        this.courseRepo = courseRepo;
//    }


    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        courseDto.setId(UUID.randomUUID().toString());
        Course course = modelMapper.map(courseDto, Course.class);
        Course savedCourse = courseRepo.save(course);

        return modelMapper.map(savedCourse, CourseDto.class);
    }

    @Override
    public CourseDto updateCourse(String id, CourseDto courseDto) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found !!"));

        modelMapper.map(courseDto,course);
        Course updatedCourse = courseRepo.save(course);

        return modelMapper.map(updatedCourse,CourseDto.class);
    }

    @Override
    public CourseDto getCoursrById(String id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Not Found"));
        return modelMapper.map(course, CourseDto.class);

    }

    @Override
    public Page<CourseDto> getAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepo.findAll(pageable);
        List<CourseDto> dtos = courses.getContent()
                .stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos,pageable,courses.getTotalElements());
    }


    @Override
    public void deleteCourse(String courseId) {
        Course course = courseRepo
                .findById(courseId)
                .orElseThrow(() ->
                        new ResourseNotFoundException("Course not found"));
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> searchCourse(String keyword) {
        List<CourseDto> courses = courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword,keyword);
        return courses.stream()
                .map(course->modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourseNotFoundException("Course Not Found"));
        String filePath = fileService.save(file, AppConstant.COURSE_BANNER_UPLOAD_DIR, file.getOriginalFilename());
        course.setBanner(filePath);
        course.setBannerContentType(file.getContentType());

        return modelMapper.map(courseRepo.save(course),CourseDto.class);
    }

    @Override
    public ResourceContentType getCourseBannerById(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new ResourseNotFoundException("Course Not Found"));

        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        System.out.println(path);
        Resource resource = new FileSystemResource(path);
        ResourceContentType resourceContentType = new ResourceContentType();
        resourceContentType.setResource(resource);

        resourceContentType.setContentType(course.getBannerContentType());
        System.out.println(resourceContentType.getContentType());
        return resourceContentType;
    }
}
