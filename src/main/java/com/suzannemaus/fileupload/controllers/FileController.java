package com.suzannemaus.fileupload.controllers;

import com.suzannemaus.fileupload.managers.IFileManager;
import com.suzannemaus.fileupload.models.FileMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
public class FileController {

    @Autowired
    private IFileManager fileManager;

    @RequestMapping(value = {"metadata/{fileId}"}, method = RequestMethod.GET)
    public ResponseEntity<List<FileMetadata>> getFileMetadata(@PathVariable("fileId") String fileId) {
        return new ResponseEntity<List<FileMetadata>>(
                this.fileManager.getFileMetadata(fileId), HttpStatus.OK);
    }

    @RequestMapping(value = {"upload"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<FileMetadata>>> uploadFileWithMetadata(
            @RequestParam(value = "metadata") String metadataListAsJsonString,
            @RequestParam(value = "file") MultipartFile file) {

        return new ResponseEntity<>(
                this.fileManager.uploadFileWithMetadata(metadataListAsJsonString, file), HttpStatus.OK);
    }

}