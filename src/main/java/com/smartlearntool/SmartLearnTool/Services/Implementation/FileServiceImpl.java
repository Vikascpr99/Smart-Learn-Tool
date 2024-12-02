package com.smartlearntool.SmartLearnTool.Services.Implementation;

import com.smartlearntool.SmartLearnTool.Services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String save(MultipartFile file, String outputPath, String fileName) throws IOException {

        Path path = Paths.get(outputPath);

        Files.createDirectories(path);

//        Joined the path
        Path filePath = Paths.get(path.toString(), file.getOriginalFilename());

        System.out.println(filePath);
        Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();

    }
}