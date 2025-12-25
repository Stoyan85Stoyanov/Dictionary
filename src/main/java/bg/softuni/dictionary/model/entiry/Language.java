package bg.softuni.dictionary.model.entiry;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "languages")
public class Language extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private LanguageName languageName;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Set<Word> words;

    public Language(LanguageName name, String description) {
        this.languageName = name;
        this.description = description;
    }
}
