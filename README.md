# file-upload-code-challenge

 Small spring boot application with two functions:
       getFileMetadata(String fileId)
           - returns list of Metadata associated with specific file
       saveFileMetadata(String metadataListAsJsonString, MultipartFile file)
           - maps file metadata appropriately, saves metadata and file
           - (actual file saving implementation waiting on requirements)