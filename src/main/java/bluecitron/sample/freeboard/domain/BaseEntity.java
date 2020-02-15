package bluecitron.sample.freeboard.domain;

import org.springframework.data.annotation.CreatedBy;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity extends BaseTimeEntity {

//    @CreatedBy
//    private Member createdBy;
}
