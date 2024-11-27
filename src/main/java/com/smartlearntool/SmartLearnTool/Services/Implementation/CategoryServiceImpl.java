package com.smartlearntool.SmartLearnTool.Services.Implementation;

import com.smartlearntool.SmartLearnTool.Repositories.CategoryRepo;
import com.smartlearntool.SmartLearnTool.Repositories.CourseRepo;
import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import com.smartlearntool.SmartLearnTool.dtos.CategoryDto;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.CustomPageResponse;
import com.smartlearntool.SmartLearnTool.entities.Category;
import com.smartlearntool.SmartLearnTool.entities.Course;
import com.smartlearntool.SmartLearnTool.exceptions.ResourseNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepo categoryRepo;
    private CourseRepo courseRepo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo,CourseRepo courseRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {

        // Create Cat id
        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);
        categoryDto.setAddedDate(new Date());

        // Convert DTO to Entity

        Category category = modelMapper.map(categoryDto, Category.class);

        Category savedCat = categoryRepo.save(category);
        return modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CustomPageResponse getAll(int pageNumber, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDir),sortBy);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sort);

        Page<Category> categoryPage = categoryRepo.findAll(pageRequest);
        List<Category> all = categoryPage.getContent();
        List<CategoryDto> categoryList = all.stream().map(category -> modelMapper.map(category, CategoryDto.class)).toList();
        CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<>();
        customPageResponse.setContent(categoryList);
        customPageResponse.setLast(categoryPage.isLast());
        customPageResponse.setTotalElements(categoryPage.getTotalElements());
        customPageResponse.setTotalPages(categoryPage.getTotalPages());
        customPageResponse.setPageSize(categoryPage.getSize());
        customPageResponse.setPageNumber(pageNumber);

        return customPageResponse;
    }

    @Override
    public CategoryDto get(String categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourseNotFoundException("Category Not Found"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourseNotFoundException("Category Not Found "));
        categoryRepo.delete(category);

    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourseNotFoundException("Category Not Found "));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        category.setAddedDate(new Date());
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    @Transactional
    public void addCourseToCategory(String catId, String courseId) {

        Category category = categoryRepo.findById(catId).orElseThrow(() -> new RuntimeException("Category Not Found"));

        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found"));
        category.addCourse(course);
        categoryRepo.save(category);
        System.out.println("Category relationship updated");

    }

    @Override
    @Transactional
    public List<CourseDto> getCoursesByCategory(String catId) {
        Category category = categoryRepo.findById(catId).orElseThrow(() -> new RuntimeException("Category Not Found"));
        List<Course> courses = category.getCourses();

        return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
    }
}
