package com.suzannemaus.fileupload.ras;

import com.suzannemaus.fileupload.models.FileMetadata;

import java.util.List;

public interface IFileMetadataResourceAccessor {

    List<FileMetadata> getFileMetadata(String fileId);

    FileMetadata saveFileMetadata(FileMetadata metadata);
}
