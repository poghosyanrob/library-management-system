package am.example.librarymanagementsystem.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class MainController {

    @Value("${library.management.system.upload.image.directory.path}")
    private String imageDirectoryPath;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal UserDetails userPrincipal,
                           ModelMap modelMap){
        Optional<UserDetails> optional = Optional.ofNullable(userPrincipal);
        optional.ifPresent(optionalUser -> {
            modelMap.addAttribute("userName", userPrincipal.getUsername());
        });
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/image/get")
    public @ResponseBody byte[] getImage(@RequestParam("pic") String picName){
        File file = new File(imageDirectoryPath + picName);
        if(file.exists()){
            try {
                return FileUtils.readFileToByteArray(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
