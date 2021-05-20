package com.gerenciamentopauta.mapper;

import com.gerenciamentopauta.dto.VotoDto;
import com.gerenciamentopauta.util.VotoEnum;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/**
 * Mapper responsavel para transformar a entidade e o dto Voto um no outro.
 */
public class VotoMapper {

    /**
     * metodo responsavel por transformar votoDto na entidade voto.
     *
     * @param votoDto Dto de voto
     * @return Voto para gerenciar internamente.
     */
    public static com.gerenciamentopauta.entity.Voto mapVoto(VotoDto votoDto) {
        return com.gerenciamentopauta.entity.Voto.builder()
            .voto(VotoEnum.valueOf(StringUtils.upperCase(votoDto.getVoto())).getValue())
            .cpf(votoDto.getCpf())
            .pautaId(votoDto.getPautaId())
            .build();
    }

    /**
     * metodo responsavel por transformar voto no Dto votoDto.
     *
     * @param voto entidade voto
     * @return VotoDto para expor externamente.
     */
    public static VotoDto mapVotoDto(com.gerenciamentopauta.entity.Voto voto) {
        VotoDto votoDto = new VotoDto();
        votoDto.setCpf(voto.getCpf());
        votoDto.setPautaId(voto.getPautaId());
        votoDto.setVoto(voto.getVoto());
        return votoDto;
    }
}
