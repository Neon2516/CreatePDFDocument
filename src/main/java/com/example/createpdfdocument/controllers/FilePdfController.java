package com.example.createpdfdocument.controllers;

import com.aspose.pdf.Document;
import com.aspose.pdf.Image;
import com.aspose.pdf.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FilePdfController {
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public @ResponseBody String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            try {
                Document doc = new Document();
                Page page = doc.getPages().add();
                Image image1 = new Image();
                image1.setImageStream(file.getInputStream());
                page.getParagraphs().add(image1);
                doc.save(System.getProperty("user.home")+ "\\Downloads\\" +name + ".pdf");
                return "Вам удалось загрузить файл " + name + ". Можете его найти у себя в загрузках";
            } catch (Exception e) {
                return "Вам не удалось загрузить " + name + " => " + e.getMessage();
            }
        } else {
            return "Вам не удалось загрузить " + name + " потому что файл пустой.";
        }
    }
}
