package com.example.praticaS7L1.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.praticaS7L1.exceptions.UploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinarySrv {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinarySrv(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map<String, Object> uploader(MultipartFile file, String folder)  {

        try {
            Map <String, Object> result =
                    cloudinary
                            .uploader()
                            .upload(file.getBytes(),
                                    ObjectUtils.asMap("folder", folder, "public_id", file.getOriginalFilename()));
            return result;
        } catch (IOException e) {
            throw new UploadException("Errore durante l'upload del file " + file.getOriginalFilename());
        }


    }
}
