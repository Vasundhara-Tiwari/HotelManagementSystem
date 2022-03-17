package controllers;

/**
 * Created by Vasundhara Tiwari
 * On 14-03-2022 at 19:08
 */


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import models.Rooms;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class RoomControllers {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Transactional
    public Result addRoom(@PathParam("number") int number, @PathParam("type") String type, @PathParam("price") int price) {

        System.out.println("add room controller using get request");

        EntityManager entityManager = entityManagerProvider.get();

        Rooms room = new Rooms(number, type, price);

        entityManager.persist(room);

        return Results.json().render("room", room);

    }
    @Transactional
    public Result addNewRoom(Rooms room) {

        System.out.println("add room controller using post request");

        EntityManager entityManager = entityManagerProvider.get();

        entityManager.persist(room);
        return Results.json().render("room", room);
    }

    @UnitOfWork
    public Result getRoom(@PathParam("number") int number) throws Exception{

        try {

            System.out.println("get room controller is running");
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();

            return Results.json().render(room);
        }
        catch (Exception e){
            return Results.json().render("No such room found");
        }
    }

    @Transactional
    public Result updateRoom(@PathParam("number") int number, Rooms inputRoom) throws Exception {

        try {
            EntityManager entityManager = entityManagerProvider.get();
            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();

            try {
                room.setPrice(inputRoom.getPrice());
                room.setType(inputRoom.getType());
            }
            catch (Exception e){
                return Results.json().render("Can't update the room number.");
            }

            entityManager.persist(room);

            return Results.json().render(room);
        }
        catch (Exception e){
            return Results.json().render("No such room found");
        }
    }

    @Transactional
    public Result deleteRoom(@PathParam("number") int number) throws Exception{

        try {
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();

            entityManager.remove(room);

            return Results.json().render("deletion successful");
        }
        catch (Exception e){
            return Results.json().render("The room you are trying to delete, doesn't exist.");
        }
    }
}