package com.sheet.management.sheet.dto;

import com.sheet.management.sheet.model.Profile;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FicheDTO {
    private Long id;
    private String title;
    private String goal;
    private String content;
    private Instant createdDate;
}
