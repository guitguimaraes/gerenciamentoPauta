package com.gerenciamentopauta.controller;

import com.gerenciamentopauta.dto.ErrorRespostaDto;
import com.gerenciamentopauta.dto.VotoDto;
import com.gerenciamentopauta.entity.Voto;
import com.gerenciamentopauta.mapper.VotoMapper;
import com.gerenciamentopauta.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static net.logstash.logback.argument.StructuredArguments.kv;

/**
 * Controller rest para serviços relacionados a voto.
 */
@Slf4j
@RestController
@Api(tags = "Voto Controller")
@RequestMapping(path = "/v1/voto")
public class VotoController {

    private final VotoService votoService;

    public VotoController(VotoService votoService) {
        this.votoService = votoService;
    }

    /**
     * Realiza voto do usuário.
     *
     * @param votoDto DTO de votação.
     * @return dto de votaçao.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Realiza o voto em uma sessão")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = VotoDto.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorRespostaDto.class),
        @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR", response = ErrorRespostaDto.class)
    })
    public VotoDto realizarVoto(@Valid @RequestBody VotoDto votoDto) {
        log.info("Request recebida para realizar um voto: {}", kv("votoAdicionado", votoDto));

        final Voto voto = votoService.realizarVotacao(VotoMapper.mapVoto(votoDto));

        log.info("{} adicionado com sucesso", kv("Voto", voto));

        return VotoMapper.mapVotoDto(voto);
    }
}
