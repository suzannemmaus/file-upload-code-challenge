package com.suzannemaus.fileupload.ras.impl;

import com.suzannemaus.fileupload.models.FileMetadata;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.suzannemaus.fileupload.ras.IFileMetadataResourceAccessor;

import java.util.List;

@Component("fileMetadataResourceAccessor")
public class FileMetadataResourceAccesor implements IFileMetadataResourceAccessor {

    protected static final Logger logger = LoggerFactory.getLogger(FileMetadataResourceAccesor.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<FileMetadata> getFileMetadata(String fileId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(FileMetadata.class);
            criteria.add(Restrictions.eq("fileId", fileId.toLowerCase()));
            List<FileMetadata> results = criteria.list();
            return results;
        } catch(NullPointerException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    @Transactional
    public FileMetadata saveFileMetadata(FileMetadata metadata) {
        metadata.setFileId(metadata.getFileId().toLowerCase());

        Session session = sessionFactory.getCurrentSession();
        session.getTransaction().begin();
        session.save(metadata);
        session.getTransaction().commit();
        return metadata;
    }

}