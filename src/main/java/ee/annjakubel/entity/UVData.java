package ee.annjakubel.entity;

import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;


@MappedEntity
@Table(name = "uv_data")
public record UVData(@Nullable
                     @Id
                     @GeneratedValue
                     Long id,
                     @NotBlank
                     double uvIndex,
                     @NotBlank
                     double longitude,
                     @NotBlank
                     double latitude,
                     @NotBlank
                     int skinType,
                     @NotBlank
                     double minutes,
                     @Column(name = "date_time")
                     LocalDateTime dateTime) { }
