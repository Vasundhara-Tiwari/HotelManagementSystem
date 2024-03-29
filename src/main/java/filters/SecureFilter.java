package filters;

import com.google.inject.Inject;
import com.google.inject.Provider;
import models.BookingDetails;
import models.Rooms;
import ninja.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by Vasundhara Tiwari
 * On 06-04-2022 at 00:55
 */

public class SecureFilter implements Filter {

    @Override
    public Result filter(FilterChain chain, Context context) {

        // if we got no cookies we break:
        if (context.getSession().get("email") == null) {
            System.out.println("Filters class if");
            return Results.redirect("/");
        } else{
            System.out.println("Filters class else");
            return chain.next(context);
        }
    }
}