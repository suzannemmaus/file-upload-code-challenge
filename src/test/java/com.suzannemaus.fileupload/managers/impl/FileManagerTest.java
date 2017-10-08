package com.suzannemaus.fileupload.managers.impl;

import com.suzannemaus.fileupload.Application;
import com.suzannemaus.fileupload.mockdata.TestFileMetadataFactory;
import com.suzannemaus.fileupload.models.FileMetadata;
import com.suzannemaus.fileupload.ras.IFileMetadataResourceAccessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class FileManagerTest {

    @Mock
    private IFileMetadataResourceAccessor ras;

    @Autowired
    @InjectMocks
    FileManager fileManager;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void test_getFileMetadataWithValidId_returnsExistingFileMetadata() {
        String uuid = UUID.randomUUID().toString();
        List<FileMetadata> fileMetadata = new ArrayList<>();
        fileMetadata.add(TestFileMetadataFactory.getTestFileMetadata());

        when(ras.getFileMetadata(uuid)).thenReturn(fileMetadata);
        List<FileMetadata> fileMetadataFound = fileManager.getFileMetadata(uuid);

        assertThat(fileMetadataFound.size(), is(equalTo(1)));
        assertThat(fileMetadataFound.get(0).getFileId(), is(equalTo(fileMetadata.get(0).getFileId())));
    }

    @Test
    public void test_updateFileMetadata_returnsUpdatedFileMetadata() {
        String uuid = UUID.randomUUID().toString();
        List<FileMetadata> fileMetadata = new ArrayList<>();
        fileMetadata.add(TestFileMetadataFactory.getTestFileMetadata());

        when(ras.getFileMetadata(uuid)).thenReturn(fileMetadata);
        when(ras.updateFileMetadata(fileMetadata.get(0))).thenReturn(TestFileMetadataFactory.getTestFileMetadata());
        List<FileMetadata> updatedFileMetadata = fileManager.updateFileMetadata(fileMetadata);

        assertThat(updatedFileMetadata.size(), is(equalTo(1)));
        assertThat(updatedFileMetadata.get(0).getFileId(), is(equalTo(fileMetadata.get(0).getFileId())));
    }

}