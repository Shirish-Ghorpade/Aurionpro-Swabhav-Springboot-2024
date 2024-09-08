//package com.aurionpro.model;
//
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.cloudinary.springboot.service.ImageService;
//
//@RestController
//public class ImageController {
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    @Autowired
//    private ImageService imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Map> upload(ImageModel imageModel) {
//        try {
//           return imageService.uploadImage(imageModel);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}