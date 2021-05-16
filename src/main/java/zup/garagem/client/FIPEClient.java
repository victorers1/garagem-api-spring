package zup.garagem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zup.garagem.dto.VeiculoFIPEDTO;

@FeignClient(name = "fipeClient", url = "https://parallelum.com.br/fipe/api/v1/carros/")
public interface FIPEClient {
    @GetMapping("marcas/{marcaId}/modelos/{modeloId}/anos/{anoCodigo}")
    VeiculoFIPEDTO getVeiculo(@PathVariable("marcaId") String marcaId,
                              @PathVariable("modeloId") String modeloId,
                              @PathVariable("anoCodigo") String anoCodigo);
}
