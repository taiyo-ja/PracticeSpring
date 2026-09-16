package org.example.practicespring.entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "employees")
@Entity
@Data
public class EmployeeEntity {

    //主キー指定
    @Id
    //オートインクリメント
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 社員登録時、
     * DBがIdを発行できるようにnull扱い可能なLongを使う
     */
    private Long id;

    private String name;

    private String email;

    private String department;
}
