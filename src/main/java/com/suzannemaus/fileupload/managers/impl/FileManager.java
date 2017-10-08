package com.suzannemaus.fileupload.managers.impl;

import com.suzannemaus.fileupload.managers.IFileManager;
import com.suzannemaus.fileupload.models.FileMetadata;
import com.suzannemaus.fileupload.ras.IFileMetadataResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.UUID.randomUUID;

@Service
public class FileManager implements IFileManager {

    @Autowired
    private IFileMetadataResourceAccessor fileMetadataResourceAccessor;

    @Override
    public List<FileMetadata> getFileMetadata(String fileId) {
        if(fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "fileId.isNull");
        }
        return fileMetadataResourceAccessor.getFileMetadata(fileId);
    }

    @Override
    public List<FileMetadata> updateFileMetadata(List<FileMetadata> metadataList) {

        List<FileMetadata> updatedFileMetadataList = new ArrayList<>();
        for(FileMetadata metadata : metadataList) {
            if(metadata.getFileId() == null) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "fileId.isNull");
            }
            FileMetadata updatedMetadata
                    = fileMetadataResourceAccessor.updateFileMetadata(metadata);
            updatedFileMetadataList.add(updatedMetadata);
        }
        return updatedFileMetadataList;
    }

    /**
     *  TODO:
     *  Determine from requirements where file contents should be stored and what file system this
     *  application is expected to be run on. This will determine the coding/filepaths declared for
     *  storing the file content.
     *
     * @param metadataList
     * @param file
     * @return
     */
    @Override
    public Map<String, List<FileMetadata>> uploadFileWithMetadata(List<FileMetadata> metadataList, MultipartFile file) {

        // This randomUUID() method is being used as a placeholder until appropriate file saving logic
        //  can be determined from requirements. This is where file saving implementation would be.
        String fileId = randomUUID().toString();

        if(fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "fileId.isNull");
        }

        Map<String, List<FileMetadata>> map = new HashMap<>();
        for(FileMetadata metadata : metadataList) {
            metadata.setId(randomUUID().toString());
            metadata.setFileId(fileId);
            metadata = fileMetadataResourceAccessor.saveFileMetadata(metadata);
        }

        map.put(fileId, metadataList);
        return map;
    }

    @Override
    public void deleteFileMetadata(String fileId) {
        if(fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "fileId.isNull");
        }
        List<FileMetadata> fileMetadataToBeDeleted
                = fileMetadataResourceAccessor.getFileMetadata(fileId);
        for(FileMetadata metadata : fileMetadataToBeDeleted) {
            fileMetadataResourceAccessor.deleteFileMetadata(metadata);
        }
    }

}