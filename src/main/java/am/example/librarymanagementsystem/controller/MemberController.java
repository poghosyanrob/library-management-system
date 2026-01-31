package am.example.librarymanagementsystem.controller;

import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Member;
import am.example.librarymanagementsystem.repository.BookRepository;
import am.example.librarymanagementsystem.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @GetMapping("/member")
    public String members(ModelMap modelMap) {
        List<Member> members = memberRepository.findAll();
        modelMap.addAttribute("members", members);
        return "member/member";
    }


    @GetMapping("/member/add")
    public String addMember(ModelMap modelMap) {
        List<Book> bookList = bookRepository.findAll();
        modelMap.addAttribute("books", bookList);
        return "member/addMember";
    }

    @PostMapping("/member/add")
    public String addMember(@ModelAttribute Member member) {
        memberRepository.save(member);
        return "redirect:/member";
    }

    @GetMapping("/member/details")
    public String memberDetails(ModelMap modelMap,
                                @RequestParam(required = false) Integer categoryId) {
        if (categoryId != null) {
            Member member = memberRepository.getOne(categoryId);
            modelMap.addAttribute("member", member);
            return "member/memberDetails";
        }
        return "redirect:/member";
    }


    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam("id") int id) {
        memberRepository.deleteById(id);
        return "redirect:/member";
    }
}
