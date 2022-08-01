package com.project.web_prj.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@Log4j2
public class UploadController {

    // upload-form.jsp로 포워딩하는 요청
    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    // 파일 업로드 처리를 위한 요청
    // MultipartFile : 클라이언트가 전송한 파일 정보들을 담은 객체
    // ex) 원본 파일명, 파일 용량, 파일 컨텐츠 타입...
    @PostMapping("/upload")
    public String upload(MultipartFile file) { // upload-form.jsp - input name = "file"
        log.info("/upload() POST! - {}", file);

        log.info("file-name: {}", file.getName());
        log.info("file-origin-name: {}", file.getOriginalFilename());
        log.info("file-size: {}", (double) file.getSize() / 1024);
        log.info("file-type: {}", file.getContentType());
        System.out.println("====================================================================================");
        
        // 서버에 업로드 파일 저장

        // 업로드 파일 저장 경로
        String uploadPath = "E:\\jy\\upload"; // 리눅스 : /, 윈도우:  \

        // 1. 세이브파일 객체 생성
        // - 첫번째 파라미터는 파일 저장경로 지정, 두번째 파일명 지정
        File f = new File(uploadPath, file.getOriginalFilename());

        try {
            file.transferTo(f);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return "redirect:/upload-form";
    }
}
