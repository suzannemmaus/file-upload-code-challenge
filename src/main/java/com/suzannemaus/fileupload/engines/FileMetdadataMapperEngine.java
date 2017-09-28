package com.suzannemaus.fileupload.engines;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suzannemaus.fileupload.models.FileMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class FileMetdadataMapperEngine {

    protected static final Logger logger = LoggerFactory.getLogger(FileMetdadataMapperEngine.class);

    public static List<FileMetadata> mapFileMetadata(String metadataAsJsonString) {
        ObjectMapper o = new ObjectMapper();
        try {
            TypeReference<List<FileMetadata>> mapType = new TypeReference<List<FileMetadata>>() {};
            return o.readValue(metadataAsJsonString, mapType);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.debug("There was an issue mapping file metadata.");
            return null;
        }
    }

}
