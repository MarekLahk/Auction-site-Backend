package com.ibay.backend.theory.b_theory.question9;

public class Docker {

    //todo A
    // What is a server?
    // Answer: A device with a purpose of serving other devices/clients. Usually lacks graphics output.

    //todo B
    // What is the difference between build server and production server?
    // Answer: Build server, as the name suggests is a server for compiling the developers code. Production server is for hosting the current live version of the software for clients to use.

    //todo C
    // What is docker?
    // Answer: In general terms it is a virtual machine stripped of all the unnecessary parts. It only contains components needed for a specific application.

    //todo D
    // Name and explain docker container benefits over virtual machine setup (java jar as system process and installed nginx)
    // 1 Requires much less resources to run
    // 2 Portability - Since containers dont need to have an OS included they are much easier to share/transmit

    //todo E
    // Name and explain docker container drawback over virtual machine setup (java jar as system process and installed nginx)
    // 1 VM-s are more secure because they share less resources and dont share the same OS. If the docker underlying OS is breached there is a good chance they are all compromised.

    //todo F
    // Name and describe tools for docker architecture
    // 1 Image - description for the creating of container
    // 2 Container - Environment where all the applications described in an image run.

    //todo G
    // Name and explain why are companies slow in moving existing systems to docker architecture (do not explain why docker is bad)
    // 1 Companies already have their systems/pipelines up and running some other way - Switching to docker architecture may require significant investment both in terms of time and money
    // 2 Security - companies may not see docker as secure enough for their application
}
