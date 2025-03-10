package com.crud.partial.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Games")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamesEntity {

    @Id
    private UUID id;

    private String gameName;
    private String gameGenre;
    private String gameYear;
    private String gameDev;

    @PrePersist
    public void generateUUID() {
        if (id == null) {
            id = UUID.randomUUID();
        }
    }

    @Override
    public String toString() {
        return "GamesEntity{" +
                "id=" + id +
                ", gameName='" + gameName + '\'' +
                ", gameGenre='" + gameGenre + '\'' +
                ", gameYear='" + gameYear + '\'' +
                ", gameDev='" + gameDev + '\'' +
                '}';
    }
}
