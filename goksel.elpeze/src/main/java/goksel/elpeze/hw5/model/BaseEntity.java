package goksel.elpeze.hw5.model;


import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Column(name = "created_on")
    @CreatedDate
    private Date createdOn;

    @Column(name = "last_changed_on")
    @LastModifiedDate
    private Date lastChangedOn;

}