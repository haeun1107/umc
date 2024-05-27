package umc.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.entity.common.BaseEntity;
import umc.spring.entity.mapping.MemberPrefer;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPrefer> memberPrefers;

    private String name;


}