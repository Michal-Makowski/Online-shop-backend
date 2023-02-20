package com.makowski.shop.entity.product;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.makowski.shop.validation.ValidationConstans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@Table(name = "review_comment")
public class ReviewComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NonNull
    @Size(min = 1, max = 254, message = ValidationConstans.NOT_SIZE)
    @Column(name = "content", nullable = false)
    private String content;
    
    @NonNull
    @Column(name = "date", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_review_id", referencedColumnName = "id")
    private ProductReview productReview;
}
