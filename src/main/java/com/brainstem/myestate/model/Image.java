package com.brainstem.myestate.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.dialect.InnoDBStorageEngine;
import org.hibernate.dialect.MySQLStorageEngine;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Entity
@Setter
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue( generator = "uuid")
    @Column(nullable = false, unique = true)
    @GenericGenerator(name = "uuid", strategy = "uuid2")

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uuid")
//    @SequenceGenerator(name = "uuid", strategy = "uuid2)
//    @Column(name = "id", nullable = false, unique = true)
    private String id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;

    public Image(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

}
