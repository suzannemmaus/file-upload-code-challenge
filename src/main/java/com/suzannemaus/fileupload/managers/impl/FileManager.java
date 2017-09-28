package com.suzannemaus.fileupload.managers.impl;

import com.suzannemaus.fileupload.managers.IFileManager;
import com.suzannemaus.fileupload.models.FileMetadata;
import com.suzannemaus.fileupload.ras.IFileMetadataResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.UUID.randomUUID;

@Service
public class FileManager implements IFileManager {

    @Autowired
    private IFileMetadataResourceAccessor fileMetadataResourceAccessor;

    /**
     *  TODO:
     *  Determine from Requirements where File Contents Should be Stored and What File System this
     *  application is expected to be run on. This will determine the coding/filepaths declated for storing
     *  the file content.
     *
     * @param metadataList
     * @param file
     * @return
     */
    @Override
    public Map<String, List<FileMetadata>> uploadFileWithMetadata(List<FileMetadata> metadataList, MultipartFile file) {

        // This randomUUID() method is being used until Appropriate File Saving Logic
        //  can be determined from Requirements
        String fileId = randomUUID().toString();

        if(fileId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "fileId.isNull");
        }

        // TODO: explain why file not saved here
        Map<String, List<FileMetadata>> map = new HashMap<>();
        for(FileMetadata metadata : metadataList) {
            metadata.setId(randomUUID().toString());
            metadata.setFileId(fileId);
            metadata = fileMetadataResourceAccessor.saveFileMetadata(metadata);
        }

        map.put(fileId, metadataList);
        return map;
    }

}