package bg.softuni.dictionary.model.entiry;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "words")
public class Word extends BaseEntity {

    @Column(nullable = false)
    private String term;

    @Column(nullable = false)
    private String translation;

    private String example;

    @Column(nullable = false)
    private LocalDate inputDate;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Language language;

    @ManyToOne(fetch = FetchType.EAGER)
    private User addedBy;

}
