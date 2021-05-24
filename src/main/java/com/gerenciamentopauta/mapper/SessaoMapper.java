package com.gerenciamentopauta.mapper;

import com.gerenciamentopauta.dto.SessaoRequestDto;
import com.gerenciamentopauta.dto.SessaoResponseDto;
import com.gerenciamentopauta.entity.Sessao;
import com.gerenciamentopauta.exception.DuracaoSessaoException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Mapper responsavel para transformar a entidade e o dto sessao um no outro.
 */
public class SessaoMapper {

    /**
     * metodo responsavel por transformar sessaoDto na entidade sessão.
     *
     * @param sessaoRequestDto Dto de sessao
     * @return Sessao para gerenciar internamente.
     */
    public static Sessao mapSessao(SessaoRequestDto sessaoRequestDto) {
        if (sessaoRequestDto.getDuracaoSessao() < 1) {
            throw new DuracaoSessaoException("Duracao da sessão deve ser maior que 0");
        }
        return Sessao.builder()
                .pautaId(sessaoRequestDto.getPautaId())
                .dataInicial(LocalDateTime.now())
                .dataFinal(LocalDateTime.now().plusMinutes(sessaoRequestDto.getDuracaoSessao()))
                .build();
    }

    /**
     * metodo responsavel por transformar sessao no Dto sessaoDto.
     *
     * @param sessao entidade sessao
     * @return SessaoDto para expor externamente.
     */
    public static SessaoResponseDto mapSessaoResponseDto(Sessao sessao) {
        SessaoResponseDto sessaoResponseDto = new SessaoResponseDto();
        sessaoResponseDto.setPautaId(sessao.getPautaId());
        sessaoResponseDto.setDataInicial(convertToDateViaInstant(sessao.getDataInicial()));
        sessaoResponseDto.setDataFinal(convertToDateViaInstant(sessao.getDataFinal()));
        sessaoResponseDto.setDuracaoSessao(Duration.between(sessao.getDataInicial(), sessao.getDataFinal()).toMinutes());
        return sessaoResponseDto;
    }

    private static String convertToDateViaInstant(LocalDateTime dateToConvert) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        return dateToConvert.format(dateTimeFormatter);
    }
}
