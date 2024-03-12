package org.grupouno.msvcnegocio.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="msvc-empleado", url = "localhost:8001/api/reserva")
public interface ReservaClienteRest {


}
