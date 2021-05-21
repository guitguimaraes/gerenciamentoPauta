package com.gerenciamentopauta.controller;

import com.gerenciamentopauta.dto.PautaResponseDto;
import com.gerenciamentopauta.service.PautaService;
import com.gerenciamentopauta.dto.PautaRequestDto;
import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.mapper.PautaMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

import static net.logstash.logback.argument.StructuredArguments.kv;

/**
 * Controller rest para serviços relacionados a pauta.
 */
@RestController
@Slf4j
@Api(tags = "Pauta Controller")
@RequestMapping(path = "/v1/pauta")
public class PautaController {

    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    /**
     * Obtem todas as Pautas criadas.
     *
     * @return lista de DTO de todas pautas criadas.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma lista de todas as pautas")
    public List<PautaResponseDto> obterPautas() {
        log.info("Request recebida para encontrar todas pautas");

        final List<Pauta> pautas = pautaService.obterPautas();

        log.info("{}  encontadas", kv("Numero de Pautas", pautas.size()));

        return pautas.stream().map(PautaMapper::mapPautaResponseDto).collect(Collectors.toList());
    }

    /**
     * obtem Pauta pelo id.
     *
     * @param pautaId pauta id para buscar pauta.
     * @return DTO da pauta encontrada.
     */
    @GetMapping("/{pautaId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna uma pauta pelo seu id")
    public PautaResponseDto obterPautaPeloPautaId(@NotBlank @PathVariable String pautaId) {
        log.info("Request recebida para encontrar uma pauta pelo {}", kv("PautaId", pautaId));

        final Pauta pauta = pautaService.obterPauta(pautaId);

        log.info("{} encontrada com sucesso", kv("Pauta", pauta));

        return PautaMapper.mapPautaResponseDto(pauta);
    }

    /**
     * Cria pauta nova.
     *
     * @param pautaRequestDto DTO de criação da pauta.
     * @return DTO de pauta criada.
     */
    @ApiOperation(value = "Cria uma nova pauta")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public PautaResponseDto criarPauta(@Valid @RequestBody PautaRequestDto pautaRequestDto) {
        log.info("Request recebido para adicionar uma pauta: {}", kv("pautaAdicionado", pautaRequestDto));

        final Pauta pauta = pautaService.criarPauta(PautaMapper.mapPauta(pautaRequestDto));

        log.info("Pauta {} adicionada com sucesso", kv("pautaAdicionado", pauta));

        return PautaMapper.mapPautaResponseDto(pauta);
    }
}
