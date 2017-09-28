package com.suzannemaus.fileupload.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * TODO: talk about lombok Data
 */

@Entity
@Table(name = "file_metadata")
@Data
public class FileMetadata implements Serializable {

    private static final long serialVersionUID = 1477347578220259538L;

    @Id
    private String id;

    private String fileId;
    private String metadataValue;
    private String metadataKey;

    public FileMetadata() {}

    public FileMetadata(String id, String fileId, String metadataValue, String metadataKey) {
        this.id = id;
        this.fileId = fileId;
        this.metadataValue = metadataValue;
        this.metadataKey = metadataKey;
    }

}