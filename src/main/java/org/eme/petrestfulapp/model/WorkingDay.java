package org.eme.petrestfulapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="WORKING_DAYS")
public class WorkingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

//    @Pattern(regexp = "MONDAY|TUESDAY|WEDNESDAY|THURSDAY|FRIDAY|SUNDAY|SATURDAY", message = "Not a valid Day!")
    private DayOfWeek dayOfWeeks;

    @Column(name = "FROM_HOURS")
    private LocalTime fromHours;


    @Column(name = "TO_HOURS")
    private LocalTime toHours;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(DayOfWeek dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public LocalTime getFromHours() {
        return fromHours;
    }

    public void setFromHours(LocalTime fromHours) {
        this.fromHours = fromHours;
    }

    public LocalTime getToHours() {
        return toHours;
    }

    public void setToHours(LocalTime toHours) {
        this.toHours = toHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkingDay that = (WorkingDay) o;
        return dayOfWeeks == that.dayOfWeeks;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayOfWeeks);
    }
}
