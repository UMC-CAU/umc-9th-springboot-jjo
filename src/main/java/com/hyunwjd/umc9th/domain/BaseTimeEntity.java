package com.hyunwjd.umc9th.domain;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;


@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {
    @UpdateTimestamp
    @Column(name = "updated_at")
    protected LocalDateTime updatedAt; // ERD: timestamp


    @Column(name = "deleted_at")
    protected LocalDateTime deletedAt; // ERD: timestamp (NULL 허용)
}