package com.camaradevelopment.media.service.models.media.folder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaFolderRepository extends JpaRepository<MediaFolder, Integer> {
        MediaFolder findById(int id);
}
