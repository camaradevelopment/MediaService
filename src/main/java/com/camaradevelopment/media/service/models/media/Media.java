package com.camaradevelopment.media.service.models.media;

import com.camaradevelopment.media.service.models.media.folder.MediaFolder;
import jakarta.persistence.*;

@Entity
@Table( name ="MEDIA")
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String mongoId;
    private String contentType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "media_folder_id", nullable = true)
    private MediaFolder mediaFolder;

    public Media() {
    }

    public Media(String name, String mongoId, String contentType) {
        this.name = name;
        this.mongoId = mongoId;
        this.contentType = contentType;
    }

    public Media(String name, String mongoId, String contentType, MediaFolder mediaFolder) {
        this.name = name;
        this.mongoId = mongoId;
        this.contentType = contentType;
        this.mediaFolder = mediaFolder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public MediaFolder getMediaFolder() {
        return mediaFolder;
    }

    public void setMediaFolder(MediaFolder mediaFolder) {
        this.mediaFolder = mediaFolder;
    }

    public int getId() {
        return id;
    }
}
