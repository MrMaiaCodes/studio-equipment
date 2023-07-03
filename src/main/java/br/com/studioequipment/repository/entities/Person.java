package br.com.studioequipment.repository.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_PERSON")
@SequenceGenerator(name = "SEQ_PERSON")

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

    @Column(name = "DT_OVERAGE")
    private boolean overage;

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
