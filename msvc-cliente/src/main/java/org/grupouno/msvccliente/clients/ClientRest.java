package org.grupouno.msvccliente.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-negocio", url = "localhost:8080/api/reserva")
public interface ClientRest {
}
