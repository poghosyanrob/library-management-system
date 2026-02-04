package am.example.librarymanagementsystem.service.impl;

import am.example.librarymanagementsystem.model.Member;
import am.example.librarymanagementsystem.repository.MemberRepository;
import am.example.librarymanagementsystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Value("${library.management.system.upload.image.directory.path}")
    private String imageDirectoryPath;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member save(Member member, MultipartFile multipartFile) {
        if(multipartFile != null && !multipartFile.isEmpty()){
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                member.setPictureName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return memberRepository.save(member);
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }


    @Override
    public Member getOne(Integer id) {
        return memberRepository.getOne(id);
    }

    @Override
    public void deleteById(Integer id) {
        memberRepository.deleteById(id);
    }

}
