package com.example.javaspringfram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller // Remove the unnecessary name "uploadFile"
public class UploadFile {
    public static final String uploadDir = "D:\\abc\\"; // Change variable name and specify the directory

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @GetMapping("/upload")
    public String upload() {
        return "uploadfile";
    }

    @PostMapping("/up-load")
    public String up(@RequestParam("anh") MultipartFile file , Model model) {
        try {

            // Create a unique filename to avoid overwriting existing files with the same name
            String path =file.getOriginalFilename();
            String getMaxIdSql = "SELECT MAX(id) FROM infor";
            Integer maxId = jdbcTemplate.queryForObject(getMaxIdSql, Integer.class);
            int id ;
            if(maxId != null){
                id = maxId +1;
            }else {
                id = 1 ;
            }
            String countSql = "SELECT COUNT(*) FROM infor WHERE path = ?";
            int rowCount = jdbcTemplate.queryForObject(countSql, Integer.class, path);
            if (rowCount > 0 || file.isEmpty()) {
                model.addAttribute("message", "Image Exist");
                return "uploadfile";
            }
            String sql = "INSERT into infor(id,path) VALUES(?,?)";
            jdbcTemplate.update(sql , id , path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "uploadfile";

    }
    @GetMapping("/displayallimages")
    public String displayAllImages(Model model) {
        try {
            String sql = "SELECT * FROM infor";
            List<Map<String, Object>> images = jdbcTemplate.queryForList(sql);
            model.addAttribute("images", images);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "uploadfile";
    }

}
