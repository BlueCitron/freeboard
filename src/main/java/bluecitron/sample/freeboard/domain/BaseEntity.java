package bluecitron.sample.freeboard.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "base_id")
    private Long id;

}
