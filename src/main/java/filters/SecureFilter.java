package filters;

import ninja.*;

/**
 * Created by Vasundhara Tiwari
 * On 06-04-2022 at 00:55
 */

public class SecureFilter implements Filter {

    @Override
    public Result filter(FilterChain chain, Context context) {

        // if we got no cookies we break:
        if (context.getSession() == null) {

            return Results.redirect("/");

        } else {
            return chain.next(context);
        }

    }
}