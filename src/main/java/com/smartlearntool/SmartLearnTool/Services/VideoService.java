package com.smartlearntool.SmartLearnTool.Services;

import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.VideoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VideoService {
    VideoDto createVideo(VideoDto videoDto);

    VideoDto updateVideo(String id,VideoDto videoDto);

    VideoDto getVideoById(String id);

    Page<VideoDto> getAllVideo(Pageable pageable);

    void deleteVideo(String id);

    List<VideoDto> searchVideos(String keyword);

//    void addCoursesToVideo (String courseId, String videoId);

}
