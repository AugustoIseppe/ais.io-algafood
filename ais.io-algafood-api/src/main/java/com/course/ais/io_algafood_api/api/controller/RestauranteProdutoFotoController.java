package com.course.ais.io_algafood_api.api.controller;

import com.course.ais.io_algafood_api.api.model.dto.input.FotoProdutoInput;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) {

        var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
        var arquivoFoto = Path.of("/Users/Augusto/Desktop/Augusto/Imagens/catalago", nomeArquivo);
        try {
            fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível salvar a foto do produto", e);
        }


    }

}
