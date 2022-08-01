package com.project.web_prj.common;

import com.project.web_prj.util.FileUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
public class UploadController {

    // 업로드 파일 저장 경로
    private static final String UPLOAD_PATH = "E:\\jy\\upload"; // 리눅스 : /, 윈도우:  \

    // upload-form.jsp로 포워딩하는 요청
    @GetMapping("/upload-form")
    public String uploadForm() {
        return "upload/upload-form";
    }

    // 파일 업로드 처리를 위한 요청
    // MultipartFile : 클라이언트가 전송한 파일 정보들을 담은 객체
    // ex) 원본 파일명, 파일 용량, 파일 컨텐츠 타입...
    @PostMapping("/upload")
    public String upload(@RequestParam("file") List<MultipartFile> fileList) { // upload-form.jsp - input name = "file"
        log.info("/upload() POST! - {}", fileList);

        for (MultipartFile file : fileList) {
            log.info("file-name: {}", file.getName());
            log.info("file-origin-name: {}", file.getOriginalFilename());
            log.info("file-size: {}", (double) file.getSize() / 1024);
            log.info("file-type: {}", file.getContentType());
            System.out.println("====================================================================================");

            // 서버에 업로드 파일 저장

            // 1. 세이브파일 객체 생성
            // - 첫번째 파라미터는 파일 저장경로 지정, 두번째 파일명 지정
            /*File f = new File(uploadPath, file.getOriginalFilename());

            try {
                file.transferTo(f);
            } catch(IOException e) {
                e.printStackTrace();
            }*/

            FileUtils.uploadFile(file,UPLOAD_PATH);
        }

        return "redirect:/upload-form";
    }

    // 비동기 요청 파일 업로드 처리
    @PostMapping("/ajax-upload")
    @ResponseBody
    public List<String> ajaxUpload(List<MultipartFile> files) {

        log.info("/ajax-upload POST! - {}", files.get(0).getOriginalFilename());

        // 클라이언트에게 전송할 파일경로 리스트
        List<String > fileNames = new ArrayList<>();

        // 클라이언트가 전송한 파일 업로드 하기
        for (MultipartFile file : files) {
            String fullPath = FileUtils.uploadFile(file, UPLOAD_PATH);
            fileNames.add(fullPath);
        }

        return fileNames;
    }
    
    // 파일 데이터 로드 요청 처리
    @GetMapping("/loadFile")
    @ResponseBody
    public void loadFile(String fileName) {
        log.info("/loadFile GET! - {}", fileName);
    }
}
