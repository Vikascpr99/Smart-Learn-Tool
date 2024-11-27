package com.smartlearntool.SmartLearnTool.Controllers;

import com.smartlearntool.SmartLearnTool.Services.VideoService;
import com.smartlearntool.SmartLearnTool.dtos.CustomMessage;
import com.smartlearntool.SmartLearnTool.dtos.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;


    @PostMapping
    public ResponseEntity<VideoDto> createVideo(@RequestBody VideoDto videoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(videoService.createVideo(videoDto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable String id, @RequestBody VideoDto videoDto){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.updateVideo(id, videoDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<VideoDto> getVideoById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(videoService.getVideoById(id));
    }
    @GetMapping
    public ResponseEntity<Page<VideoDto>> getAllVideo(Pageable pageable){
        return ResponseEntity.ok(videoService.getAllVideo(pageable));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable String id){
        videoService.deleteVideo(id);
        return ResponseEntity.noContent().build();
    }
//    @PostMapping("/{videoId}/course/{courseId}")
//    public ResponseEntity<CustomMessage> videosToCourse(
//            @PathVariable String videoId,
//            @PathVariable String courseId
//    ){
//        videoService.addCoursesToVideo(videoId,courseId);
//        CustomMessage customMessage = new CustomMessage();
//        customMessage.setMessage("Video Updated !!");
//        customMessage.setSuccess(true);
//        return ResponseEntity.ok(customMessage);
//    }
}
