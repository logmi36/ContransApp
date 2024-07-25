package com.pe.ctrapp5.Handler;


import com.pe.ctrapp5.Model.DocItem;
import com.pe.ctrapp5.Model.Obj01;
import com.pe.ctrapp5.Model.Obj04;
import com.pe.ctrapp5.Model.Obj05;
import com.pe.ctrapp5.Model.Obj06;
import com.pe.ctrapp5.Model.Obj07;
import com.pe.ctrapp5.Model.Obj08;
import com.pe.ctrapp5.Model.Obj09;
import com.pe.ctrapp5.Model.Obj10;
import com.pe.ctrapp5.Model.Obj11;
import com.pe.ctrapp5.Model.Obj12;
import com.pe.ctrapp5.Model.Obj13;
import com.pe.ctrapp5.Model.Obj14;
import com.pe.ctrapp5.Model.Obj15;
import com.pe.ctrapp5.Model.Obj18;
import com.pe.ctrapp5.Model.Obj19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Srv01 {


    //VehiculoA
    @POST("api/vehiculo/buscar")
    Call<List<Obj04>> fnc05(@Body DocItem item);

    //VehiculoB
    @POST("api/vehiculo/mostrar")
    Call <Obj05> fnc06(@Body DocItem item);

    @POST("api/vehiculo/imagenregistrar")
    Call <DocItem> fnc07(@Body DocItem item);

    @POST("api/vehiculo/imageneslistar")
    Call <List<Obj06>> fnc08(@Body DocItem item);


    @POST("api/vehiculo/imagenenliminar")
    Call <DocItem> fnc09(@Body DocItem item);


    @POST("api/vehiculo/imagenenliminartodos")
    Call <DocItem> fnc10(@Body DocItem item);


    @POST("api/vehiculo/insertar")
    Call <Obj04> fnc32(@Body DocItem item);


    @POST("api/vehiculo/choferlistar")
    Call<List<Obj04>> fnc37(@Body DocItem item);


    // ===============================================================

    @POST("api/conductor/listar")
    Call <List<Obj07>> fnc11(@Body DocItem item);

    @POST("api/conductor/obtener")
    Call <Obj08> fnc12(@Body DocItem item);

    @POST("api/conductor/imagenregistrar")
    Call <DocItem> fnc13(@Body DocItem item);


    @POST("api/conductor/registrar")
    Call <Obj07> fnc30(@Body DocItem item);


    @POST("api/conductor/actualizar")
    Call <DocItem> fnc31(@Body DocItem item);

    @POST("api/conductor/agregarvehiculo")
    Call <DocItem> fnc33(@Body DocItem item);

    // ===============================================================

    @POST("api/contenedor/listar")
    Call <List<Obj09>> fnc14(@Body DocItem item);

    @POST("api/contenedor/mostrar")
    Call <Obj10> fnc15(@Body DocItem item);

    @POST("api/contenedor/imagenregistrar")
    Call <DocItem> fnc16(@Body DocItem item);

    @POST("api/contenedor/imagenlistar")
    Call <List<Obj11>> fnc17(@Body DocItem item);

    // ===============================================================

    @POST("api/cita/mostrar")
    Call <Obj12> fnc18(@Body DocItem item);

    @POST("api/cita/actualizararribo")
    Call <DocItem> fnc19(@Body DocItem item);

    @POST("api/cita/actualizaringreso")
    Call <DocItem> fnc20(@Body DocItem item);

    @POST("api/cita/actualizarsalida")
    Call <DocItem> fnc21(@Body DocItem item);

    @POST("api/cita/listar")
    Call <List<Obj13>> fnc24(@Body DocItem item);


    @POST("api/cita/mostrarxid")
    Call <Obj13> fnc25(@Body DocItem item);


    @POST("api/cita/imprimir")
    Call <DocItem> fnc26(@Body DocItem item);


    @POST("api/cita/insertar")
    Call <DocItem> fnc27(@Body Obj15 item);


    @POST("api/cita/eliminar")
    Call <DocItem> fnc28(@Body DocItem item);


    @POST("api/cita/editar")
    Call <DocItem> fnc29(@Body DocItem item);


    // ===============================================================

    @POST("api/empresa/listar")
    Call <List<Obj14>> fnc22(@Body DocItem item);


    // ===============================================================

    @POST("api/usuario/registrar")
    Call <DocItem> fnc23(@Body Obj01 item);


    // ===============================================================


    @POST("api/documento/listar")
    Call <List<Obj18>> fnc34(@Body DocItem item);



    @POST("api/documento/insertar")
    Call <Obj18> fnc35(@Body DocItem item);

    @POST("api/documento/listarcontenedor")
    Call <List<Obj09>> fnc36(@Body DocItem item);


    // ===============================================================


    @POST("api/notificacion/listar")
    Call <List<Obj19>> fnc38(@Body DocItem item);


}

