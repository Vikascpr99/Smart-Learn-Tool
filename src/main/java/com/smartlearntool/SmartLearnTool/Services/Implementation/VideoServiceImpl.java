package com.smartlearntool.SmartLearnTool.Services.Implementation;

import com.smartlearntool.SmartLearnTool.Repositories.CourseRepo;
import com.smartlearntool.SmartLearnTool.Repositories.VideoRepo;
import com.smartlearntool.SmartLearnTool.Services.VideoService;
import com.smartlearntool.SmartLearnTool.dtos.CourseDto;
import com.smartlearntool.SmartLearnTool.dtos.VideoDto;
import com.smartlearntool.SmartLearnTool.entities.Course;
import com.smartlearntool.SmartLearnTool.entities.Video;
import com.smartlearntool.SmartLearnTool.exceptions.ResourseNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {


    @Autowired
    private VideoRepo videoRepo;
    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setVideoId(UUID.randomUUID().toString());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideos = videoRepo.save(video);
        return modelMapper.map(savedVideos,VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(String id, VideoDto videoDto) {
        Video video = videoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));
        modelMapper.map(videoDto, video);

        video.setShortDesc(videoDto.getShortDesc());
        video.setContentType(videoDto.getContentType());
        video.setTitle(videoDto.getTitle());
        video.setContentType(videoDto.getContentType());

        Video updateVideo = videoRepo.save(video);
        return modelMapper.map(updateVideo,VideoDto.class);
    }

    @Override
    public VideoDto getVideoById(String id) {
        Video video = videoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Video Not Found"));

        return modelMapper.map(video,VideoDto.class);
    }

    @Override
    public Page<VideoDto> getAllVideo(Pageable pageable) {
        Page<Video> videos = videoRepo.findAll(pageable);
        List<VideoDto> dtos = videos.getContent()
                .stream()
                .map(video -> modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
        return new PageImpl<>(dtos,pageable,videos.getTotalElements());
    }

    @Override
    public void deleteVideo(String id) {
        Video video = videoRepo.findById(id)
                .orElseThrow(() -> new ResourseNotFoundException("Video not Found"));
        videoRepo.delete(video);

    }

    @Override
    public List<VideoDto> searchVideos(String keyword) {
        List<VideoDto> videos = videoRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword,keyword);
        return videos.stream()
                .map(video->modelMapper.map(video, VideoDto.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public void addCoursesToVideo(String videoId, String courseId) {
//        Course course = courseRepo.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found !!"));
//        Video video = videoRepo.findById(videoId).orElseThrow(() -> new RuntimeException("Video Not Found !!!"));
//////        video.addCourse(course);
////        course.addVideo(video);
//////        videoRepo.save(video);
//        System.out.println("Video Added to Course");
//    }


}
