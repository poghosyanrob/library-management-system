package am.example.librarymanagementsystem.controller;

import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Member;
import am.example.librarymanagementsystem.service.BookService;
import am.example.librarymanagementsystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BookService bookService;


    @GetMapping("/member")
    public String members(ModelMap modelMap) {
        List<Member> members = memberService.findAll();
        modelMap.addAttribute("members", members);
        return "member/member";
    }


    @GetMapping("/member/add")
    public String addMember(ModelMap modelMap) {
        List<Book> bookList = bookService.findAll();
        modelMap.addAttribute("books", bookList);
        return "member/addMember";
    }

    @PostMapping("/member/add")
    public String addMember(@ModelAttribute Member member,
                            @RequestParam("pic")MultipartFile multipartFile) {
        memberService.save(member,multipartFile);
        return "redirect:/member";
    }

    @GetMapping("/member/details")
    public String memberDetails(ModelMap modelMap,
                                @RequestParam(required = false) Integer memberId) {
        if (memberId != null) {
            Member member = memberService.getOne(memberId);
            modelMap.addAttribute("member", member);
            return "member/memberDetails";
        }
        return "redirect:/member";
    }

    @GetMapping("/member/details/book/delete")
    public String deleteMemberDetailsBook(
                                @RequestParam Integer memberId,
                                @RequestParam Integer bookId) {
        if (memberId != null && bookId != null) {
            Member member = memberService.getOne(memberId);
            member.getBooks().removeIf(book -> book.getId() == bookId);

            memberService.save(member);

        }
        return "redirect:/member/details?categoryId=" + memberId;
    }


    @GetMapping("/member/delete")
    public String deleteMember(@RequestParam("id") int id) {
        memberService.deleteById(id);
        return "redirect:/member";
    }
}
