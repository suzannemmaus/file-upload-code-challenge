package com.suzannemaus.fileupload.managers;

import com.suzannemaus.fileupload.models.FileMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IFileManager {

    List<FileMetadata> getFileMetadata(String fileId);

    List<FileMetadata> updateFileMetadata(List<FileMetadata> metadata);

    Map<String, List<FileMetadata>> uploadFileWithMetadata(List<FileMetadata> metadata, MultipartFile file);

    void deleteFileMetadata(String fileId);

}
