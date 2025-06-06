package org.gdsc.globook.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdsc.globook.domain.type.ELanguage;
import org.gdsc.globook.domain.type.ELoginProvider;
import org.gdsc.globook.domain.type.EUserRole;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "login_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    private ELoginProvider provider;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    // ------ Mapping ------ //
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserBook> userBooks = new ArrayList<>();

    @Builder(access = AccessLevel.PRIVATE)
    public User(String email, String password, String nickname, ELoginProvider provider) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.provider = provider;
        this.role = EUserRole.USER;
    }

    public static User create(String email, String password, String nickname, ELoginProvider provider) {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .provider(provider)
                .build();
    }
}
