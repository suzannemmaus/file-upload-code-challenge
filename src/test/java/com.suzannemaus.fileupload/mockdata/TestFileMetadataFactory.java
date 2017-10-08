package com.suzannemaus.fileupload.mockdata;

import com.suzannemaus.fileupload.models.FileMetadata;

import java.util.UUID;

public class TestFileMetadataFactory {

    public final static String FILE_ID_FOR_EXISTING_FILE_METADATA = "dc3c1123-bc0f-4b49-80b4-758dd15b2f5c";

    public static FileMetadata getTestFileMetadata() {
        FileMetadata metadata = new FileMetadata();
        metadata.setId(UUID.randomUUID().toString());
        metadata.setFileId(FILE_ID_FOR_EXISTING_FILE_METADATA);
        metadata.setMetadataKey("Additional File Info");
        metadata.setMetadataValue("test value");
        return metadata;
    }

}