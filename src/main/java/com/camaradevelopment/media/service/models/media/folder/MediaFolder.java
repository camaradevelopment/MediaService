package com.camaradevelopment.media.service.models.media.folder;

import com.camaradevelopment.media.service.models.media.Media;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "MEDIA_FOLDER")
public class MediaFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String Name;

    @OneToMany(mappedBy = "mediaFolder")
    private List<Media> mediaList;

    public MediaFolder() {
    }

    public MediaFolder(String name) {
        Name = name;
    }

    public MediaFolder(String name, List<Media> mediaList) {
        Name = name;
        this.mediaList = mediaList;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MediaFolder that = (MediaFolder) o;

        if (id != that.id) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getMediaList() != null ? getMediaList().equals(that.getMediaList()) : that.getMediaList() == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getMediaList() != null ? getMediaList().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MediaFolder{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", mediaList=" + mediaList +
                '}';
    }

    public void copy(MediaFolder folder) {
        this.setName(folder.getName());
        this.setMediaList(folder.getMediaList());
    }
}
