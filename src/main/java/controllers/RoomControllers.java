package controllers;

/**
 * Created by Vasundhara Tiwari
 * On 14-03-2022 at 19:08
 */


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import filters.SecureFilter;
import models.Rooms;
import ninja.Context;
import ninja.FilterWith;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.PathParam;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomControllers {
    @Inject
    Provider<EntityManager> entityManagerProvider;

    @Inject
    CorsHeadersController cors;

    @Transactional
    public Result addRoom(@PathParam("number") int number, @PathParam("type") String type, @PathParam("price") int price, @PathParam("available") boolean available) {

        System.out.println("add room controller using get request");

        EntityManager entityManager = entityManagerProvider.get();

        Rooms room = new Rooms(number, type, price, available);

        entityManager.persist(room);

        return cors.addHeaders(Results.json().render("room", room));

    }

    @Transactional
    @FilterWith(SecureFilter.class)
    public Result addNewRoom(Rooms room, Context context){

            System.out.println("add room controller using post request");

            EntityManager entityManager = entityManagerProvider.get();

            entityManager.persist(room);
            return cors.addHeaders(Results.json().render("room", room));

    }

    @UnitOfWork
    public Result getRoom(@PathParam("number") int number) throws Exception{

        try {

            System.out.println("get room controller is running");
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();

            return cors.addHeaders(Results.json().render(room));
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render("No such room found"));
        }
    }

    public Result getAllRooms() throws Exception{

        try {
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Rooms> q = entityManager.createQuery("select x from Rooms x order by x.number", Rooms.class);
            List<Rooms> rooms = q.getResultList();

            return cors.addHeaders(Results.json().render(rooms));
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render("No rooms present in the database."));
        }
    }

    @Transactional
    public Result bookRoom(int number) {

        EntityManager entityManager = entityManagerProvider.get();
        TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

        Rooms returnedRoom = query.setParameter("number", number).getSingleResult();

        if(!returnedRoom.isAvailable()){
            returnedRoom.setAvailable(true);
            entityManager.persist(returnedRoom);
            return cors.addHeaders(Results.json().render("Booked"));
        }
        return cors.addHeaders(Results.json().render("can't book"));
    }

    @FilterWith(SecureFilter.class)
    @Transactional
    public Result updateRoom(@PathParam("number") int number, Rooms inputRoom) throws Exception {

        System.out.println("update room is running");
        System.out.println(inputRoom.getNumber()+"---"+inputRoom.getPrice()+ "----"+inputRoom.getPrice());

        try {
            EntityManager entityManager = entityManagerProvider.get();
            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();
                room.setPrice(inputRoom.getPrice());
                room.setType(inputRoom.getType());
                room.setAvailable(inputRoom.isAvailable());

            entityManager.persist(room);

            return cors.addHeaders(Results.json().render(room));
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render("No such room found"));
        }
    }


    @Transactional
    public Result deleteRoom(@PathParam("number") int number) throws Exception{

        try {
            EntityManager entityManager = entityManagerProvider.get();

            TypedQuery<Rooms> query = entityManager.createQuery("SELECT x from Rooms x where x.number = :number", Rooms.class);

            Rooms room = query.setParameter("number", number).getSingleResult();

            entityManager.remove(room);

            return cors.addHeaders(Results.json().render("deletion successful"));
        }
        catch (Exception e){
            return cors.addHeaders(Results.json().render("The room you are trying to delete, doesn't exist."));
        }
    }
}