package controllers;

import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.Param;
import ninja.session.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by Vasundhara Tiwari
 * On 19-03-2022 at 00:13
 */

@Singleton
public class LoginLogoutController {
    @Inject
    GuestController guest;

    public Result login(Context context) {

        return Results.html();

    }

    public Result loginPost(@Param("username") String username,
                            @Param("password") String password,
                            @Param("rememberMe") Boolean rememberMe,
                            Context context) {

        boolean isUserNameAndPasswordValid = guest.isUserAndPasswordValid(username, password);

        if (isUserNameAndPasswordValid) {
            Session session = context.getSession();
            session.put("username", username);

            if (rememberMe != null && rememberMe) {
                session.setExpiryTime(24 * 60 * 60 * 1000L);
            }

            context.getFlashScope().success("login.loginSuccessful");

            return Results.redirect("/");

        } else {

            // something is wrong with the input or password not found.
            context.getFlashScope().put("username", username);
            context.getFlashScope().put("rememberMe", String.valueOf(rememberMe));
            context.getFlashScope().error("login.errorLogin");

            return Results.redirect("/login");

        }

    }
}