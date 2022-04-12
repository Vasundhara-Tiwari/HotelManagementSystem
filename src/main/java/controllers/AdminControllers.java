package controllers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import models.BookingDetails;
import ninja.Result;
import ninja.Results;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Vasundhara Tiwari
 * On 12-04-2022 at 18:37
 */

public class AdminControllers {

    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Inject
    CorsHeadersController cors;


    @Transactional
    public Result getBookingDetails() throws Exception{
        try {
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<BookingDetails> q = entityManager.createQuery("SELECT x from BookingDetails x", BookingDetails.class);
            List<BookingDetails> bookings = q.getResultList();

            return cors.addHeaders(Results.json().render(bookings));
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render("No bookings found."));
        }
    }
}