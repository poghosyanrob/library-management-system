package am.example.librarymanagementsystem.controller;

import am.example.librarymanagementsystem.model.User;
import am.example.librarymanagementsystem.service.UserService;
import am.example.librarymanagementsystem.service.security.SpringUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {

    @Value("${library.management.system.upload.image.directory.path}")
    private String imageDirectoryPath;
    private final UserService userService;

    @GetMapping("/")
    public String mainPage(@AuthenticationPrincipal SpringUser userPrincipal,
                           ModelMap modelMap){
        Optional<UserDetails> optional = Optional.ofNullable(userPrincipal);
        optional.ifPresent(optionalUser -> {
            modelMap.addAttribute("user", userPrincipal.getUser());
        });
        return "index";
    }

    @GetMapping("/loginPage")
    public String loginPage(@RequestParam(required = false) String msg,ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
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

    @GetMapping("/registerPage")
    public String registerPage(@RequestParam(required = false) String msg, ModelMap modelMap) {
        modelMap.addAttribute("msg", msg);
        return "registerPage";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return "redirect:/registerPage?msg=Username already exists!";
        }
        userService.save(user);
        return "redirect:/loginPage?msg=Registration successful, pls login!";
    }

}
