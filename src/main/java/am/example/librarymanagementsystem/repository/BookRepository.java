package am.example.librarymanagementsystem.repository;

import am.example.librarymanagementsystem.model.Book;
import am.example.librarymanagementsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Integer> {


    List<Book> findByCategory(Category category);

    @Query("""
    SELECT b FROM Book b
    WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
       OR LOWER(b.author) LIKE LOWER(CONCAT('%', :keyword, '%'))
""")
    List<Book> searchBooks(@Param("keyword") String keyword);


}
