package bg.softuni.dictionary.init;


import bg.softuni.dictionary.model.entiry.Language;
import bg.softuni.dictionary.model.entiry.LanguageName;
import bg.softuni.dictionary.model.entiry.repository.LanguageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {


    private final Map<LanguageName, String> careerInformation = Map.of(
            LanguageName.GERMAN, "A West Germanic language, is spoken by over 90 million people worldwide. Known for its complex grammar and compound words, it's the official language of Germany and widely used in Europe.",

            LanguageName.SPANISH, "A Romance language, is spoken by over 460 million people worldwide. It boasts a rich history, diverse dialects, and is known for its melodious sound, making it a global cultural treasure.",

            LanguageName.FRENCH, "A Romance language spoken worldwide, known for its elegance and cultural richness. It's the official language of France and numerous nations, famed for its cuisine, art, and literature.",

            LanguageName.ITALIAN, "A Romance language spoken in Italy and parts of Switzerland, with rich cultural heritage. Known for its melodious sounds, it's a gateway to Italian art, cuisine, and history."

    );

    private final LanguageRepository languageRepository;

    public InitService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }


    @Override
    public void run(String... args)  {

        long count = this.languageRepository.count();

        if (count > 0) {
            return;
        }

        List<Language> toInsert = Arrays.stream(LanguageName.values())
                .map(name -> new Language(name, careerInformation.get(name))).toList();

        this.languageRepository.saveAll(toInsert);
    }
}
