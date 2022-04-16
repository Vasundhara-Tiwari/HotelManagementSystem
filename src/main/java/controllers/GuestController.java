package controllers;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.google.inject.persist.Transactional;
import models.BookingDetails;
import models.Guest;
import ninja.*;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created by Vasundhara Tiwari
 * On 19-03-2022 at 00:08
 */


public class GuestController {

    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Inject
    CorsHeadersController cors;

    @Transactional
    public Result addGuest(Guest guest) throws Exception {

        try {
            System.out.println("add guest controller using post request");

            EntityManager entityManager = entityManagerProvider.get();

            entityManager.persist(guest);
            return cors.addHeaders(Results.json().render("guest", guest));
        }
        catch (Exception e) {
            return cors.addHeaders(Results.json().render("User already exists"));
        }
    }

    public boolean isUserAndPasswordValid(String email, String password) {

        if (email != null && password != null) {

            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Guest> q = entityManager.createQuery("SELECT x FROM Guest x WHERE email = :emailParam", Guest.class);
            Guest guest = getSingleResult(q.setParameter("emailParam", email));
            if (guest != null) {
                if (guest.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transactional
    public Result addBooking(BookingDetails bookingDetails){
        System.out.println(bookingDetails.getBookingDate());
        try {
            System.out.println("add");
            EntityManager entityManager = entityManagerProvider.get();
            entityManager.persist(bookingDetails);
            return cors.addHeaders(Results.json().render("detail", bookingDetails));
        }
        catch (Exception e){
            System.out.println(e);
            return cors.addHeaders(Results.json().render(e));
        }
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