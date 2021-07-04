package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
//        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id and u.user.id=:user_id "),
//        @NamedQuery(name = Meal.GET, query =  "SELECT u FROM Meal u JOIN FETCH u.user   WHERE u.id=:id and u.user.id=:user_id"),
//        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u JOIN FETCH u.user WHERE  u.user.id=:user_id ORDER BY u.dateTime"),

        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal u WHERE u.id=:id and u.user.id=:user_id "),
        @NamedQuery(name = Meal.GET, query =  "SELECT u FROM Meal u WHERE  u.user.id=:user_id"),
        @NamedQuery(name = Meal.GET_BY_ID, query =  "SELECT u FROM Meal u WHERE u.id=:id and u.user.id=:user_id"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT u FROM Meal u WHERE u.user.id=:user_id ORDER BY u.dateTime"),
        @NamedQuery(name = Meal.GET_BETWEEN, query = "SELECT u FROM Meal u WHERE u.user.id=:user_id and u.dateTime>=:startDateTime and u.dateTime<=:endDateTime ORDER BY u.dateTime DESC"),
})

@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String GET = "Meal.get";
    public static final String GET_BETWEEN = "Meal.get_between";
    public static final String GET_BY_ID = "Meal.get_by_id";
    public static final String ALL_SORTED = "Meal.all_sorted";

@Column(name = "date_time", nullable = false)
@NotNull
    private LocalDateTime dateTime;

@Column(name = "description", nullable = false)
@NotBlank
@Size(min=2, max = 120)
    private String description;

@Column(name = "calories", nullable = false)
@Range(min=5, max=5000)
    private int calories;

    @ManyToOne(fetch = FetchType.LAZY/*, cascade = {CascadeType.MERGE}*/)
    @JoinColumn(name="user_id", nullable = false)
    @NotNull
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {

        System.out.println("return user");
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
