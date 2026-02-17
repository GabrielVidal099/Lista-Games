package com.example.games.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(
        name = "games",
        indexes = {
                @Index(name = "idx_games_title", columnList = "title"),
                @Index(name = "idx_games_genre", columnList = "genre"),
                @Index(name = "idx_games_release_date", columnList = "release_date")
        }
)
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 120)
    @Column(nullable = false, length = 120)
    private String title;

    @Size(max = 2000)
    @Column(length = 2000)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Genre genre;

    @Size(max = 80)
    @Column(length = 80)
    private String developer;

    @Size(max = 80)
    @Column(length = 80)
    private String publisher;

    @PastOrPresent
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    @Column(precision = 12, scale = 2)
    private BigDecimal price;

    @Size(max = 500)
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    /**
     * Nota de 0.0 a 10.0
     */
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    @Digits(integer = 2, fraction = 1)
    @Column(precision = 3, scale = 1)
    private BigDecimal rating;

    @NotNull
    @Column(nullable = false)
    private Boolean active = true;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private Instant updatedAt;

    public enum Genre {
        ACTION,
        ADVENTURE,
        RPG,
        SPORTS,
        RACING,
        STRATEGY,
        SIMULATION,
        PUZZLE,
        HORROR,
        FPS,
        FIGHTING,
        INDIE,
        OTHER
    }
}
