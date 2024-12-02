package com.smartlearntool.SmartLearnTool.Services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String save (MultipartFile file, String outputPath, String fileName) throws IOException;
}
