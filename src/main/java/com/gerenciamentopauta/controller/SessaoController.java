package com.gerenciamentopauta.controller;

import com.gerenciamentopauta.dto.SessaoRequestDto;
import com.gerenciamentopauta.mapper.SessaoMapper;
import com.gerenciamentopauta.service.SessaoService;
import com.gerenciamentopauta.dto.ErrorRespostaDto;
import com.gerenciamentopauta.dto.ResultadoVotacaoDto;
import com.gerenciamentopauta.dto.SessaoResponseDto;
import com.gerenciamentopauta.entity.Sessao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
 * Controller rest para serviços relacionados a sessão.
 */
@Slf4j
@RestController
@Api(tags = "Sessao Controller")
@RequestMapping(path = "/v1/sessao")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    /**
     * Obtem todas as Sessões criadas.
     *
     * @return lista de DTO de todas sessões criadas.
     */
    @GetMapping
    @ApiOperation(value = "Retorna uma lista de todas as Sessões")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = SessaoResponseDto.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorRespostaDto.class)
    })
    public List<SessaoResponseDto> obterSessoes() {
        log.info("Request recebida para encontrar todas Sessões");

        final List<Sessao> sessoes = sessaoService.obterSessoes();

        log.info("{}  encontadas", kv("Numero de Sessões", sessoes.size()));

        return sessoes.stream().map(SessaoMapper::mapSessaoResponseDto).collect(Collectors.toList());
    }

    /**
     * obtem Sessao pela pauta id.
     *
     * @param pautaId pauta id para buscar sessao.
     * @return DTO da sessao encontrada.
     */
    @GetMapping("/{pautaId}")
    @ApiOperation(value = "Retorna uma sessão pelo id da sua pauta")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = SessaoResponseDto.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorRespostaDto.class),
        @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR", response = ErrorRespostaDto.class)
    })
    public SessaoResponseDto obtemSessaoPorPautaId(@NotBlank @PathVariable String pautaId) {

        log.info("Request recebida para encontrar uma sessao pelo Id: " + pautaId);

        Sessao sessao = sessaoService.obterSessaoPelaPautaId(pautaId);

        log.info("{} encontrada com sucesso", kv("Sessao", sessao));

        return SessaoMapper.mapSessaoResponseDto(sessao);
    }

    /**
     * Abre uma sessão nova.
     *
     * @param sessaoRequestDto DTO de criação da sessão.
     * @return DTO de sessão criada.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Abre uma sessão de votação para uma pauta")
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = SessaoResponseDto.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorRespostaDto.class),
        @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR", response = ErrorRespostaDto.class)
    })
    public SessaoResponseDto abrirSessao(@Valid @RequestBody SessaoRequestDto sessaoRequestDto) {
        log.info("Request recebido para iniciar uma sessao: {}", kv("sessaoAdicionada", sessaoRequestDto));

        final Sessao sessao = sessaoService.criarSessao(SessaoMapper.mapSessao(sessaoRequestDto));

        log.info(" {} adicionada com sucesso", kv("Sessao", sessao));

        return SessaoMapper.mapSessaoResponseDto(sessao);
    }

    /**
     * Abre uma sessão nova.
     *
     * @param pautaId id da pauta que deseja obter resultado.
     * @return DTO resultado da votação.
     */
    @GetMapping("/{pautaId}/resultado")
    @ApiOperation(value = "Retorna o resultado de uma votação de uma sessão pelo id da sua pauta")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
        @ApiResponse(code = 200, message = "OK", response = SessaoResponseDto.class),
        @ApiResponse(code = 400, message = "BAD_REQUEST", response = ErrorRespostaDto.class),
        @ApiResponse(code = 500, message = "INTERNAL_SERVER_ERROR", response = ErrorRespostaDto.class)
    })
    public ResultadoVotacaoDto obtemResultadoSessaoPelaPautaId(@NotBlank @PathVariable String pautaId) {
        return sessaoService.obtemResultadoSessaoPelaPautaId(pautaId);
    }

}
