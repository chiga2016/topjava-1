package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
//        if (user.isNew()) {
//            em.persist(user);
//            return user;
//        } else {
//            return em.merge(user);
//        }
       em.persist(meal);
       return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return (Meal) em.createNamedQuery(Meal.GET)
                .setParameter("id", id)
                .setParameter("user_id", userId).getSingleResult();
        //return em.find(Meal.class,id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return  em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAll(userId).stream()
                .filter(p->p.getDateTime().isAfter(startDateTime))
                .filter(p->p.getDateTime().isBefore(endDateTime))
                .collect(Collectors.toList());
    }
}