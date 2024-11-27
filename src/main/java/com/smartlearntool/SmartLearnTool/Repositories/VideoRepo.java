package com.smartlearntool.SmartLearnTool.Repositories;

import com.smartlearntool.SmartLearnTool.dtos.VideoDto;
import com.smartlearntool.SmartLearnTool.entities.Course;
import com.smartlearntool.SmartLearnTool.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepo extends JpaRepository<Video, String>
{
    Optional<Video> findByTitle(String title);



    List<VideoDto> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String key1, String key2);

    List<Video> findByCourseId(String courseId);
}
