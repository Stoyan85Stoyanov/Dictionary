package bg.softuni.dictionary.model.entiry.repository;

import bg.softuni.dictionary.model.entiry.Language;
import bg.softuni.dictionary.model.entiry.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Optional<Language> findByLanguageName(LanguageName name);
}
