package com.gerenciamentopauta.mapper;

import com.gerenciamentopauta.dto.SessaoDto;
import com.gerenciamentopauta.entity.Sessao;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Mapper responsavel para transformar a entidade e o dto sessao um no outro.
 */
public class SessaoMapper {

    /**
     * metodo responsavel por transformar sessaoDto na entidade sessão.
     *
     * @param sessaoDto Dto de sessao
     * @return Sessao para gerenciar internamente.
     */
    public static Sessao mapSessao(SessaoDto sessaoDto) {
        return Sessao.builder()
            .pautaId(sessaoDto.getPautaId())
            .dataInicial(LocalDateTime.now())
            .dataFinal(LocalDateTime.now().plusMinutes(sessaoDto.getDuracaoSessao()))
            .build();
    }

    /**
     * metodo responsavel por transformar sessao no Dto sessaoDto.
     *
     * @param sessao entidade sessao
     * @return SessaoDto para expor externamente.
     */
    public static SessaoDto mapSessaoDto(Sessao sessao) {
        SessaoDto sessaoDto = new SessaoDto();
        sessaoDto.setPautaId(sessao.getPautaId());
        sessaoDto.setDataInicial(sessao.getDataInicial());
        sessaoDto.setDataFinal(sessao.getDataFinal());
        sessaoDto.setDuracaoSessao(Duration.between(sessao.getDataInicial(), sessao.getDataFinal()).toMinutes());
        return sessaoDto;
    }
}
