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


import controllers.ApplicationController;
import controllers.GuestController;
import controllers.RoomControllers;
import controllers.LoginLogoutController;
import ninja.Router;
import ninja.application.ApplicationRoutes;


public class Routes implements ApplicationRoutes {

    @Override
    public void init(Router router) {

        router.GET().route("/").with(ApplicationController::helloWorldJson);

        //for adding room using GET request
        router.GET().route("/addRoom/{number}/{type}/{price}").with(RoomControllers::addRoom);

        //for adding room using POST request
        router.POST().route("/addNewRoom").with(RoomControllers::addNewRoom);

        // for fetching room for the database
        router.GET().route("/getRoom/{number}").with(RoomControllers::getRoom);

        // for updating room's data in the database
        router.PUT().route("/updateRoom/{number}").with(RoomControllers::updateRoom);

        // for deleting the room's in the database
        router.DELETE().route("/deleteRoom/{number}").with(RoomControllers::deleteRoom);

        // signup route
        router.POST().route("/guest/signup").with(GuestController :: addGuest);

        //login route
        router.GET().route("/login").with(LoginLogoutController::loginPost);

        //logout route
        router.GET().route("/logout").with(LoginLogoutController::logout);
    }
}
