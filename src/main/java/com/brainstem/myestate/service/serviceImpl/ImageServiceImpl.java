package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.payload.ImageResponse;
import com.brainstem.myestate.repository.ImageRepository;
import com.brainstem.myestate.service.ImagesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ImageServiceImpl implements ImagesService {

    ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image saveImage(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        try{
            if(fileName.contains("..")){
                throw new Exception("Filename contains invalid path sequence" + fileName);
            }

            Image imageObject = new Image(fileName, file.getContentType(), file.getBytes(), file.getSize());
            Image imageEntity = imageRepository.save(imageObject);

            String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/v1/user/download/")
                    .path(imageEntity.getId())
                    .toUriString();
            imageRepository.findById(imageEntity.getId()).get().setUrl(imageUrl);
            return imageEntity;

        }catch (Exception e){
            throw new Exception("Could not save file" + fileName);
        }

    }

    @Override
    public List<Image> saveImages(MultipartFile[] files) throws Exception {
         int size = files.length, i = 0;
         ArrayList<Image> listOfImages = new ArrayList<>();
         while( i < size){
             listOfImages.add(saveImage(files[i]));
             i++;
         }
         return  listOfImages;
    }

    @Override
    public Image getImage(String fileId) throws Exception {
        return imageRepository.findById(fileId).orElseThrow(
                ()-> new Exception("File not found with Id: " + fileId)
        );
    }
}
