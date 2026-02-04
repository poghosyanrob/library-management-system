package am.example.librarymanagementsystem.repository;

import am.example.librarymanagementsystem.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {

}
