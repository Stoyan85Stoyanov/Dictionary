package bg.softuni.dictionary.service;

import bg.softuni.dictionary.config.UserSession;
import bg.softuni.dictionary.dto.AddWordDto;
import bg.softuni.dictionary.model.entiry.Language;
import bg.softuni.dictionary.model.entiry.LanguageName;
import bg.softuni.dictionary.model.entiry.User;
import bg.softuni.dictionary.model.entiry.Word;
import bg.softuni.dictionary.model.entiry.repository.LanguageRepository;
import bg.softuni.dictionary.model.entiry.repository.UserRepository;
import bg.softuni.dictionary.model.entiry.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class WordService {

    private final UserSession userSession;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final WordRepository wordRepository;


    public WordService(UserSession userSession, UserRepository userRepository, LanguageRepository languageRepository, WordRepository wordRepository) {
        this.userSession = userSession;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
        this.wordRepository = wordRepository;
    }

    public boolean create(AddWordDto data) {

        if (!userSession.isLoggedIn()) {
            return false;
        }

        Optional<User> byId = userRepository.findById(userSession.id());

        if (byId.isEmpty()) {
            return false;
        }

        Optional<Language> byName = languageRepository.findByLanguageName(LanguageName.valueOf(data.getLanguage()));

        if (byName.isEmpty()) {
            return false;
        }

        Word word = new Word();
        word.setTerm(data.getTerm());
        word.setTranslation(data.getTranslation());
        word.setExample(data.getExample());
        word.setInputDate(data.getInputDate());
        word.setLanguage(byName.get());
        word.setAddedBy(byId.get());

        wordRepository.save(word);
        return true;
    }

    public Map<LanguageName, List<Word>> findWordByLanguage() {
        Map<LanguageName, List<Word>> result = new HashMap<>();

        List<Language> allLanguages = languageRepository.findAll();

        for (Language language : allLanguages) {
            List<Word> words = wordRepository.findWordByLanguage(language);
            result.put(language.getLanguageName(), words);
        }
        return result;
    }

    public List<Word> listWords(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        return wordRepository.findAllByAddedBy(user);
    }

    public void delete(Long id) {
        wordRepository.deleteById(id);
    }
}
