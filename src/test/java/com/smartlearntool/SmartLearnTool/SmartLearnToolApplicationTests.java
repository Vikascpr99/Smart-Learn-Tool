package com.smartlearntool.SmartLearnTool;

import com.smartlearntool.SmartLearnTool.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmartLearnToolApplicationTests {

	@Autowired
	private CategoryService categoryService;
	@Test
	void contextLoads() {
	}

	@Test
	public void testCategoryCourseRelation(){
		categoryService.addCourseToCategory("7d976827-9528-4293-9576-7440e99f38dc","8b848c80-497f-4c9c-a9d4-2b40274b52e7");
	}

}
