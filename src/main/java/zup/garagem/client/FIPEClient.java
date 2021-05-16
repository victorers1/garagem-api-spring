package zup.garagem.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zup.garagem.dto.VeiculoDTO;

import java.util.List;

@FeignClient(name = "fipeClient", url = "https://parallelum.com.br/fipe/api/v1/carros/")
public interface FIPEClient {
    @GetMapping("marcas/{marcaId}/modelos/{modeloId}/anos/{anoCodigo}")
    List<VeiculoDTO> getVeiculo(@PathVariable Integer marcaId, @PathVariable Integer modeloId, @PathVariable String anoCodigo);
}
