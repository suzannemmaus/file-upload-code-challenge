package com.suzannemaus.fileupload.ras;

import com.suzannemaus.fileupload.models.FileMetadata;

import java.util.List;

public interface IFileMetadataResourceAccessor {

    List<FileMetadata> getFileMetadata(String fileId);

    FileMetadata updateFileMetadata(FileMetadata metadata);

    FileMetadata saveFileMetadata(FileMetadata metadata);

    void deleteFileMetadata(FileMetadata metadata);
}
