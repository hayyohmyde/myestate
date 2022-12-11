package com.brainstem.myestate.service.serviceImpl;

import com.brainstem.myestate.model.Image;
import com.brainstem.myestate.repository.ImageRepository;
import com.brainstem.myestate.service.ImagesService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

            Image image = new Image(fileName, file.getContentType(), file.getBytes());
            return imageRepository.save(image);

        }catch (Exception e){
            throw new Exception("Could not save file" + fileName);
        }

    }

    @Override
    public ArrayList<Image> saveImages(MultipartFile[] files) {
        return null;
    }

    @Override
    public Image getImage(String field) throws Exception {
        return imageRepository.findById(Long.valueOf(field)).orElseThrow(
                ()-> new Exception("File not found with Id: " + field)
        );
    }
}
