package com.cybertek.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false) //==> IOT keep the insertion date fixed when updating
    private LocalDateTime insertDateTime;

    @Column(nullable = false, updatable = false)
    private Long insertUserId;

    @Column(nullable = false)
    private LocalDateTime lastUpdateDateTime;

    @Column(nullable = false)
    private Long lastUpdateUserId;

    private Boolean isDeleted=false;

    @PrePersist
    private void onPrePersist(){
        this.insertDateTime = LocalDateTime.now();
        this.lastUpdateDateTime=LocalDateTime.now();
        this.insertUserId=1L;
        this.lastUpdateUserId=1L;
    }

    @PreUpdate
    private void onPreUpdate(){
        this.lastUpdateDateTime=LocalDateTime.now();
        this.lastUpdateUserId=1L;
    }
}
