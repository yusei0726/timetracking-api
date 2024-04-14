package com.example.timetrackingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * テーブルの共通項目を定義したクラスです。</br>
 * 全てのEntityクラスはこのクラスを継承して作成します。
 */
@MappedSuperclass
@Data
public class CommonEntity {

    @Column(name = "createdAt")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    @PrePersist
    public void preInsert() {
        Date date = new Date();
        setCreatedAt(date);
        setUpdatedAt(date);
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedAt(new Date());
    }

}
