package bluecitron.sample.freeboard.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AttributeOverride(name = "id", column = @Column(name = "category_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category extends BaseEntity {

    @NonNull
    private String title;

    private Integer hasPostCount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> children = new ArrayList<>();

    protected Category(String title) {
        this.title = title;
        this.hasPostCount = 0;
    }

    public static Category create(String title) {
        return new Category(title);
    }

    public void setParent(Category parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }

    public void increasePostCount() {
        this.hasPostCount += 1;
    }

    public void decreasePostCount() {
        this.hasPostCount -= 1;
    }

}
