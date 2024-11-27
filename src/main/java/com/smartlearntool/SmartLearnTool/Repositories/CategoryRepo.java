package com.smartlearntool.SmartLearnTool.Repositories;

import com.smartlearntool.SmartLearnTool.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, String>
{

}
