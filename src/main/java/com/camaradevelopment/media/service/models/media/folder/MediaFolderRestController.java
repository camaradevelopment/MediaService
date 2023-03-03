package com.camaradevelopment.media.service.models.media.folder;

import com.camaradevelopment.media.service.models.media.Media;
import com.camaradevelopment.media.service.models.media.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/media/folder")
public class MediaFolderRestController {

    @Autowired
    private MediaFolderRepository mediaFolderRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public void createMediaFolder(@RequestBody MediaFolder mediaFolder){
        mediaFolderRepository.save(mediaFolder);
    }

    public MediaFolder getMediaFolderById(@PathVariable int mediaFolderId){
        return mediaFolderRepository.findById(mediaFolderId);
    }

    public List<MediaFolder> getAllMediaFolders(){
        return mediaFolderRepository.findAll();
    }

    public void deleteFolderById(@PathVariable int mediaFolderId){
        mediaFolderRepository.deleteById(mediaFolderId);
    }

    public void updateFolder(@RequestBody MediaFolder mediaFolder){
        MediaFolder tempMediaFolder = mediaFolderRepository.findById(mediaFolder.getId());
        tempMediaFolder.copy(mediaFolder);
        mediaFolderRepository.save(tempMediaFolder);
    }

    public void addMediaToMediaFolder(@PathVariable int mediaId, @PathVariable int mediaFolderId){
        Media mediaToAdd = mediaRepository.findById(mediaId);
        MediaFolder mediaFolderToAddMediaTo = mediaFolderRepository.findById(mediaFolderId);
        mediaFolderToAddMediaTo.getMediaList().add(mediaToAdd);
        List<Media> mediaListWithNewMedia = mediaFolderToAddMediaTo.getMediaList();
        mediaFolderToAddMediaTo.setMediaList(mediaListWithNewMedia);
        mediaFolderRepository.save(mediaFolderToAddMediaTo);
    }
}
