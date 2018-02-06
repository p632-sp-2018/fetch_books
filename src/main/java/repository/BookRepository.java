package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Book;

public interface BookRepository extends JpaRepository<Book, String> {

}
