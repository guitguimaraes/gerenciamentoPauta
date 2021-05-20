package com.gerenciamentopauta.mapper;

import com.gerenciamentopauta.entity.Pauta;
import com.gerenciamentopauta.dto.PautaDto;

/**
 * Mapper responsavel para transformar a entidade e o dto pauta um no outro.
 */
public class PautaMapper {
    /**
     * metodo responsavel por transformar pautaDto na entidade pauta.
     *
     * @param pautaDto Dto de pauta
     * @return Pauta para gerenciar internamente.
     */
    public static Pauta mapPauta(PautaDto pautaDto) {
        return Pauta.builder()
            .descricao(pautaDto.getDescricao())
            .nome(pautaDto.getNome())
            .build();
    }

    /**
     * metodo responsavel por transformar pauta no Dto pautaDto.
     *
     * @param pauta entidade pauta
     * @return PautaDto para expor externamente.
     */
    public static PautaDto mapPautaDto(Pauta pauta) {
        PautaDto pautaDto = new PautaDto();
        pautaDto.setPautaId(pauta.getPautaId());
        pautaDto.setDescricao(pauta.getDescricao());
        pautaDto.setNome(pauta.getNome());
        return pautaDto;
    }
}
