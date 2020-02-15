package bluecitron.sample.freeboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @NonNull
    private String title;

    private Integer hasPostCount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    public Category(Category parent, String title) {
        this.parent = parent;
        this.title = title;
        this.hasPostCount = 0;
        parent.getChildren().add(this);
    }

    public void increasePostCount() {
        this.hasPostCount += 1;
    }

    public void decreasePostCount() {
        this.hasPostCount -= 1;
    }

}
