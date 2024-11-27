package com.smartlearntool.SmartLearnTool.Controllers;

import com.smartlearntool.SmartLearnTool.Config.AppConstant;
import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import com.smartlearntool.SmartLearnTool.dtos.CategoryDto;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.CustomMessage;
import com.smartlearntool.SmartLearnTool.dtos.CustomPageResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController (CategoryService categoryService){
        this.categoryService = categoryService;
    }
//  Create Category
    @PostMapping
    public ResponseEntity<CategoryDto> create (@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto createdDto = categoryService.insert(categoryDto);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDto);
    }
//    Get Category
    @GetMapping
    public CustomPageResponse<CategoryDto> getAll(
            @RequestParam (value = "pageNumber", required = false, defaultValue = AppConstant.DEFAULT_PAGE_NUMBER)int pageNumber,
            @RequestParam (value = "pageSize", required = false, defaultValue = AppConstant.DEFAULT_PAGE_SIZE) int pageSize,
            @RequestParam (value = "sortBy", required = false, defaultValue = AppConstant.DEFAULT_SORT_BY) String sortBy,
            @RequestParam (value = "sortDir", required = false, defaultValue = AppConstant.DEFAULT_SORT_DIR) String sortDir
    ){
        return categoryService.getAll(pageNumber,pageSize,sortBy,sortDir);
    }

//    Get Category By Id
    @GetMapping("/{categoryId}")
    public CategoryDto getCategoryById(@PathVariable String categoryId){

        return categoryService.get(categoryId);
    }

//    Delete Category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage> delete(@PathVariable String categoryId)
    {
        categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category deleted successfully");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }
//    Update
    @PutMapping("/{categoryId}")
    public CategoryDto update(@PathVariable String categoryId,
                              @RequestBody CategoryDto categoryDto)
    {
        return categoryService.update(categoryDto, categoryId);
    }

    @PostMapping("/{categoryId}/courses/{courseId}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
            @PathVariable String categoryId,
            @PathVariable String courseId
    ){
        categoryService.addCourseToCategory(categoryId,courseId);

        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("Category Updated !!");
        customMessage.setSuccess(true);
        return ResponseEntity.ok(customMessage);
    }

    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<List<CourseDto>> getCourseByCategory(
            @PathVariable String categoryId
    ){
        return ResponseEntity.ok(categoryService.getCoursesByCategory(categoryId));
    }


}
