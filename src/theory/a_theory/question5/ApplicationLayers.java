package com.ibay.backend.theory.a_theory.question5;

public class ApplicationLayers {

    //todo
    // Architects insist on having layered architecture in the back-end... ¯\_(ツ)_/¯

    //todo p1
    // Name 3 layers of back-end architecture. Give a brief description for each.
    // 1 API/Controller
    // Description: Deals with user requests
    // 2 Business/Service
    // Description: Makes decisions for the requests
    // 3 Data/DAO(data access service)
    // Description:	Provides data to the service

    //todo p2
    // Do you agree with the architects? Why?
    // Yes
    // Because: It logically separates the project into components where each has a
	//			very specific and different task to do.

    //todo p3
    // We use objects to transport data between different layers.
    // What is the difference between Entity and Dto? What is same between them?
    // Answer: Dto-s are only for transporting data. They are not used for any kind of
	//			logic. They are created from or for user requests. Entities are 
	//			for the backend for making decisions and creating dto-s for the user to see.

}
