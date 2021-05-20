package com.gerenciamentopauta.handler;

import com.gerenciamentopauta.exception.CpfException;
import com.gerenciamentopauta.exception.InelegivelVotarException;
import com.gerenciamentopauta.exception.NotFoundException;
import com.gerenciamentopauta.exception.SessaoAbertaException;
import com.gerenciamentopauta.exception.SessaoFechadaException;
import com.gerenciamentopauta.exception.SessaoInexistenteException;
import com.gerenciamentopauta.dto.ErrorRespostaDto;
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
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({SessaoFechadaException.class, CpfException.class, InelegivelVotarException.class,
        SessaoAbertaException.class, SessaoInexistenteException.class})
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
