package com.smartlearntool.SmartLearnTool.Services;

import com.smartlearntool.SmartLearnTool.dtos.CategoryDto;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.CustomPageResponse;

import java.util.List;

public interface CategoryService {



    CategoryDto insert (CategoryDto categoryDto);

    CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir);

    CategoryDto get(String categoryId);

    void delete (String categoryId);

    CategoryDto update (CategoryDto categoryDto, String categoryId);

    public void addCourseToCategory(String catId, String course);

    List<CourseDto> getCoursesByCategory (String catId);


}
