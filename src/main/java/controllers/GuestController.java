package controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Guest;
import ninja.jpa.UnitOfWork;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by Vasundhara Tiwari
 * On 19-03-2022 at 00:08
 */


public class GuestController {

    @Inject
    Provider<EntityManager> entityManagerProvider;

    @UnitOfWork
    public boolean isUserAndPasswordValid(String username, String password) {

        if (username != null && password != null) {

            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Guest> q = entityManager.createQuery("SELECT x FROM Guest x WHERE username = :usernameParam", Guest.class);
            Guest guest = getSingleResult(q.setParameter("usernameParam", username));
            if (guest != null) {
                if (guest.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static <T> T getSingleResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }
}