package com.gerenciamentopauta.handler;

import com.gerenciamentopauta.dto.ErrorRespostaDto;
import com.gerenciamentopauta.exception.CpfException;
import com.gerenciamentopauta.exception.DuracaoSessaoException;
import com.gerenciamentopauta.exception.InelegivelVotarException;
import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.exception.PautaExistenteException;
import com.gerenciamentopauta.exception.SessaoAbertaException;
import com.gerenciamentopauta.exception.SessaoFechadaException;
import com.gerenciamentopauta.exception.SessaoInexistenteException;
import com.gerenciamentopauta.exception.VotoExistenteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controle para tratar todas as exceptions lançadas pela aplicação.
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SessaoFechadaException.class, CpfException.class, InelegivelVotarException.class,
        SessaoAbertaException.class, SessaoInexistenteException.class, PautaExistenteException.class,
        VotoExistenteException.class, IllegalArgumentException.class, DuracaoSessaoException.class})
    public ResponseEntity<ErrorRespostaDto> handleInternalServerError(Exception exception) {
        return handleException(exception, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorRespostaDto> handleNotFound(Exception exception) {
        return handleException(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorRespostaDto> handleException(Exception exception, HttpStatus httpStatus) {
        final ErrorRespostaDto errorRespostaDto = ErrorRespostaDto.builder().mensagem(exception.getMessage()).build();
        return ResponseEntity.status(httpStatus).body(errorRespostaDto);
    }

}
