package com.suzannemaus.fileupload.controllers;

import com.suzannemaus.fileupload.managers.impl.FileManager;
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
    private FileManager fileManager;

    @RequestMapping(value = {"upload"}, method = RequestMethod.POST)
    public ResponseEntity<Map<String, List<FileMetadata>>> uploadFileWithMetadata(
            @RequestBody List<FileMetadata> metadata,
            @RequestParam(value = "file") MultipartFile file) {

        return new ResponseEntity<>(this.fileManager.uploadFileWithMetadata(metadata, file), HttpStatus.OK);
    }

}