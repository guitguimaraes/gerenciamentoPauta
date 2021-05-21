package com.gerenciamentopauta.mapper;

import com.gerenciamentopauta.dto.PautaResponseDto;
import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.dto.PautaRequestDto;

/**
 * Mapper responsavel para transformar a entidade e o dto pauta um no outro.
 */
public class PautaMapper {
    /**
     * metodo responsavel por transformar pautaDto na entidade pauta.
     *
     * @param pautaRequestDto Dto de pauta
     * @return Pauta para gerenciar internamente.
     */
    public static Pauta mapPauta(PautaRequestDto pautaRequestDto) {
        return Pauta.builder()
            .descricao(pautaRequestDto.getDescricao())
            .nome(pautaRequestDto.getNome())
            .build();
    }

    /**
     * metodo responsavel por transformar pauta no Dto pautaDto.
     *
     * @param pauta entidade pauta
     * @return PautaResponseDto para expor externamente.
     */
    public static PautaResponseDto mapPautaResponseDto(Pauta pauta) {
        PautaResponseDto pautaDtoResponse = new PautaResponseDto();
        pautaDtoResponse.setPautaId(pauta.getPautaId());
        pautaDtoResponse.setDescricao(pauta.getDescricao());
        pautaDtoResponse.setNome(pauta.getNome());
        return pautaDtoResponse;
    }
}
