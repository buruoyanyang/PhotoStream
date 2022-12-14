package photo.stream.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import photo.stream.service.FileStorageService;

/**
 * @author xuhf
 * @date 2022/9/21
 */
@Tag(name = "首页模块")
@RestController
public class TestController {

    @Autowired
    private FileStorageService fileStorageService;
    @Parameter(name = "name",description = "姓名",required = true)
    @Operation(summary = "向客人问好")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

    @Parameter(name = "file",description = "文件",required = true)
    @Operation(summary = "上传文件")
    @PostMapping("/upload")
    public void upload(MultipartFile file) {
        fileStorageService.upload(file);
    }
}
