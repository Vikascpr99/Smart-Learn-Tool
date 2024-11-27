package com.smartlearntool.SmartLearnTool.Repositories;

import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, String>
{
    Optional<Course> findByTitle(String title);

    List<Course> findByLive(boolean live);

    List<Course> findByDiscount(double discount);

    List<CourseDto> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String keyword, String keyword1);

//    @Query("select c from Course c where c.category.id")
//    List<Course> findByCategoryId(String categoryId);
}
