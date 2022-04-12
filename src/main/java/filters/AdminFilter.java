package filters;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.BookingDetails;
import ninja.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * Created by Vasundhara Tiwari
 * On 12-04-2022 at 19:56
 */

public class AdminFilter implements Filter{

    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Override
    public Result filter(FilterChain chain, Context context)  {

        String email = context.getSession().get("email");


        System.out.println("email-----"+context.getSession().get("email"));
        if (context.getSession().get("email") != null) {
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<BookingDetails> query = entityManager.createQuery("SELECT x from BookingDetails x where x.email = :email", BookingDetails.class);

            BookingDetails bookingDetails = query.setParameter("email", email).getSingleResult();

            if(bookingDetails.getEmail() == "new@gmail.com"){
                return chain.next(context);
            }
            System.out.println("Filters class if");
            return chain.next(context);

        } else{
            System.out.println("Filters class else");
            return Results.redirect("/");
        }
    }
}