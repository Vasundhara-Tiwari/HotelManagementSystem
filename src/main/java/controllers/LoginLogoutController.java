package controllers;

import com.google.inject.Provider;
import models.Guest;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import ninja.params.PathParam;
import ninja.session.Session;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    @Inject
    Provider<EntityManager> entityManagerProvider;

    public Result login(Context context) {

        return cors.addHeaders(Results.html());

    }

    public Result loginPost(Guest guest1, Session session) throws Exception{
        try {
            System.out.println(guest1.getEmail() + " " + guest1.getPassword());
            boolean isUserNameAndPasswordValid = guest.isUserAndPasswordValid(guest1.getEmail(), guest1.getPassword());

            if (isUserNameAndPasswordValid) {
                session.put("email", guest1.getEmail());
                System.out.println(session.get("email"));
                return cors.addHeaders(Results.json().render("Logged In"));

            } else {
                return cors.addHeaders(Results.json().render("Error logging in"));
            }
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render(e));
        }
    }

    public Result isAdmin(Session session){
        try {
            EntityManager entityManager = entityManagerProvider.get();
            String email = session.get("email");
            System.out.println(email);
            TypedQuery<Guest> q = entityManager.createQuery("SELECT x FROM Guest x WHERE email = :emailParam", Guest.class);
            Guest guest = getSingleResult(q.setParameter("emailParam", email));
            System.out.println(guest.isAdmin());
            if (guest.isAdmin()) {
                return cors.addHeaders(Results.json().render(true));
            } else {
                return cors.addHeaders(Results.json().render(false));
            }
        }
        catch (Exception e){
            System.out.println("Can't resolve " + e);
            return cors.addHeaders(Results.json().render(false));
        }
    }

    public Result isLoggedIn(Session session){
        System.out.println(session.get("email"));
        if(session.get("email") != null){
            System.out.println("login is true");
            return cors.addHeaders(Results.json().render(true));
        }
        else {
            System.out.println("login is false");
            return cors.addHeaders(Results.json().render(false));
        }
    }

    public Result logout(Context context) {

        // remove any user dependent information
        context.getSession().clear();
        System.out.println("Logout!!!");
        return cors.addHeaders(Results.json().render("Logged Out"));

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