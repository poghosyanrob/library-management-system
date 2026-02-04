package am.example.librarymanagementsystem.service;

import am.example.librarymanagementsystem.model.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MemberService {
    List<Member> findAll();
    Member save(Member member,MultipartFile multipartFile);
    Member save(Member member);
    Member getOne(Integer id);
    void deleteById(Integer id);


}
