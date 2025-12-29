package bg.softuni.dictionary.model.entiry.repository;


import bg.softuni.dictionary.model.entiry.Language;
import bg.softuni.dictionary.model.entiry.User;
import bg.softuni.dictionary.model.entiry.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, String> {

//    Optional<Word> findByName(Language language);


    List<Word> findWordByLanguage(Language language);

    List<Word> findAllByAddedBy(User addedBy);

    void deleteById(Long id);
}
