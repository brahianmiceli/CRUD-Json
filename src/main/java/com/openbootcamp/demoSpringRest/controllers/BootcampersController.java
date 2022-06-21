package com.openbootcamp.demoSpringRest.controllers;

import com.openbootcamp.demoSpringRest.models.Bootcamper;
import com.openbootcamp.demoSpringRest.services.BootcamperService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
@Path("/")
public class BootcampersController {

    //CONECTA CON EL PACKAGE SERVICE DESDE CONTROLLERS
    // PARA UTILIZAR SERVICIOS
    private final BootcamperService bootcamperService;

    public BootcampersController(BootcamperService bootcamperService){
        this.bootcamperService = bootcamperService;
        // METE USUARIOS PREDETERMINADOS
        this.bootcamperService.add(new Bootcamper("1", Math.random()));
        this.bootcamperService.add(new Bootcamper("2", Math.random()));
        this.bootcamperService.add(new Bootcamper("3", Math.random()));
        this.bootcamperService.add(new Bootcamper("4", Math.random()));
        this.bootcamperService.add(new Bootcamper("5", Math.random()));

    }

    @GET //LISTA TODOS LOS USUARIOS
    @Path("/bootcampers")
    @Produces("application/json")
    public List<Bootcamper> listarTodos(){
        return bootcamperService.getAll();
    }

    @GET
    @Path("/bootcampers/{nombre}")
    @Produces("application/json")
    public Bootcamper listarUno(@PathParam("nombre") String nombre){
        return bootcamperService.get(nombre);
    }

    @POST //METER USUARIOS A LA LISTA
    @Path("/bootcampers")
    @Produces("application/json")
    @Consumes("application/json")
    public Response meterBootcamper(Bootcamper bootcamper){

        bootcamperService.add(bootcamper);

        return Response.created(
                URI.create("/bootcampers/" + bootcamper.getNombre())
        ).build();
    }
}

