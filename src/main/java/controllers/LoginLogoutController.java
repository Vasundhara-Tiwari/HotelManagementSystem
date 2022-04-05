package controllers;

import models.Guest;
import ninja.Context;
import ninja.Result;
import ninja.Results;
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

    @Inject
    CorsHeadersController cors;

    public Result login(Context context) {

        return cors.addHeaders(Results.html());

    }

    public Result loginPost(Guest guest1, Context context) {

        System.out.println(guest1.getEmail() + " " + guest1.getPassword());
        boolean isUserNameAndPasswordValid = guest.isUserAndPasswordValid(guest1.getEmail(), guest1.getPassword());

        if (isUserNameAndPasswordValid) {
            Session session = context.getSession();
            session.put("email", guest1.getEmail());

            context.getFlashScope().success("login.loginSuccessful");

            return cors.addHeaders(Results.json().render("Logged In"));

        } else {

            // something is wrong with the input or password not found.
            context.getFlashScope().put("email", guest1.getEmail());
            context.getFlashScope().error("login.errorLogin");

            return cors.addHeaders(Results.json().render("Error logging in"));
        }
    }


    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();
        context.getFlashScope().success("login.logoutSuccessful");

        System.out.println("Logout!!!");

        return cors.addHeaders(Results.redirect("/"));

    }
}