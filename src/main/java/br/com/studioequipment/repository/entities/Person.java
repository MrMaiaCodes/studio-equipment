package br.com.studioequipment.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_PERSON")
@GenericGenerator(
        name = "SEQ_PERSON",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "SEQ_PERSON"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    @Column(name = "ID_PERSON")
    private Long id;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DT_AGE")
    private Long age;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID")
    private List<Equipment> equipments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
