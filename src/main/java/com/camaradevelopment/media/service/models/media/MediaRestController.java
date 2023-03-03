package com.camaradevelopment.media.service.models.media;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(path = "/media")
@Service
public class MediaRestController {

    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    GridFsOperations gridFsOperations;

    @Autowired
    MediaRepository mediaRepository;

    @Value("#{${supported-conent-types}}")
    private List<String> supportedConentTypes;

    @RequestMapping(path = "/{mediaId}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getMedia(@PathVariable int mediaId){
        Media media = mediaRepository.findById(mediaId);
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(media.getMongoId())));
        try{
            return ResponseEntity.ok().contentLength(gridFSFile.getLength())
                    .contentType(MediaType.parseMediaType(media.getContentType()))
                    .body(new InputStreamResource((gridFsTemplate.getResource(gridFSFile).getInputStream())));
        }catch (IOException e){
            throw new NullPointerException("No File Found");
        }
    }

    @RequestMapping(path ="/delete/{mediaId}", method = RequestMethod.DELETE)
    public void deleteMedia(@PathVariable int mediaId) {
        try{
            Media media = mediaRepository.findById(mediaId);
            gridFsOperations.delete(new Query(Criteria.where("_id").is(media.getMongoId())));
            mediaRepository.delete(media);
        }catch (Exception e)
        {
            throw new NullPointerException(e.getMessage());
        }

    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    private Media saveMedia(@RequestParam("file") MultipartFile file) {
        DBObject metaData = new BasicDBObject();
        if (file.getContentType() != null && supportedConentTypes.contains(file.getContentType())) {
                switch (file.getContentType()) {
                    case "audio/mpeg" -> metaData.put("type", "mp3");
                    case "video/mp4" -> metaData.put("type", "mp4");
                }
                try {
                    InputStream mp4Stream = file.getInputStream();
                    String videoId = gridFsTemplate.store(mp4Stream, file.getOriginalFilename(), file.getContentType(), metaData).toString();
                    Media media = new Media(file.getOriginalFilename(), videoId, file.getContentType());
                    mediaRepository.save(media);
                    return media;
                } catch (IOException ioException) {
                    System.out.println(ioException);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }
        return new Media();
    }
}
