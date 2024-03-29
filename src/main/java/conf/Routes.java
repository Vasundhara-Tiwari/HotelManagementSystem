/**
 * Copyright (C) the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package conf;


import controllers.*;
import filters.AdminFilter;
import filters.SecureFilter;
import ninja.Router;
import ninja.application.ApplicationRoutes;


public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {
        router.OPTIONS().route("/addNewRoom").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/guest/signup").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/getAllRooms").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/getRoom/{number}").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/updateRoom/{number}").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/deleteRoom/{number}").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/login").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/logout").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/bookRoom").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/isLoggedIn").with(CorsHeadersController.class, "routeForOptions");
        router.OPTIONS().route("/isAdmin").with(CorsHeadersController.class, "routeForOptions");
        ///////////
        router.OPTIONS().route("/getBookings").with(CorsHeadersController.class, "routeForOptions");
        ////////////
        router.OPTIONS().route("/addBooking").with(CorsHeadersController.class, "routeForOptions");

        router.GET().route("/").with(ApplicationController::helloWorldJson);

        /////////////
        router.GET().route("/getBookings").with(AdminControllers::getBookingDetails);
        /////////////

        router.GET().route("/getAllRooms").with(RoomControllers::getAllRooms);
        router.POST().route("/addBooking").with(GuestController::addBooking);

        router.GET().route("/isLoggedIn").with(LoginLogoutController::isLoggedIn);

        router.GET().route("/isAdmin").with(LoginLogoutController::isAdmin);

        router.POST().route("/addNewRoom").filters(SecureFilter.class).with(RoomControllers::addNewRoom);

        // for fetching room for the database
        router.GET().route("/getRoom/{number}").with(RoomControllers::getRoom);

        //for booking room
        router.POST().route("/bookRoom").filters(SecureFilter.class).with(RoomControllers::bookRoom);

        // for updating room's data in the database
        router.PUT().route("/updateRoom/{number}").filters(SecureFilter.class).with(RoomControllers::updateRoom);

        // for deleting the room's in the database
        router.DELETE().route("/deleteRoom/{number}").filters(SecureFilter.class).with(RoomControllers::deleteRoom);

        // signup route
        router.POST().route("/guest/signup").with(GuestController :: addGuest);

        //login route
        router.POST().route("/login").with(LoginLogoutController::loginPost);

        //logout route
        router.GET().route("/logout").with(LoginLogoutController::logout);
    }
}
