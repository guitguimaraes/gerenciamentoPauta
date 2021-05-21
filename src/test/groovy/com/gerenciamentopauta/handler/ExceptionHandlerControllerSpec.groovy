package com.gerenciamentopauta.handler

import com.gerenciamentopauta.dto.ErrorRespostaDto
import com.gerenciamentopauta.exception.CpfException
import com.gerenciamentopauta.exception.InelegivelVotarException
import com.gerenciamentopauta.exception.NotFoundException
import com.gerenciamentopauta.exception.PautaExistenteException
import com.gerenciamentopauta.exception.SessaoAbertaException
import com.gerenciamentopauta.exception.SessaoFechadaException
import com.gerenciamentopauta.exception.SessaoInexistenteException
import com.gerenciamentopauta.exception.VotoExistenteException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class ExceptionHandlerControllerSpec extends Specification {

    ExceptionHandlerController controllerHandler = new ExceptionHandlerController()

    def 'SessaoFechadaException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        SessaoFechadaException sessaoException = new SessaoFechadaException('Sessao Fechada Exception')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(sessaoException)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'Sessao Fechada Exception'

    }

    def 'CpfException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        CpfException cpfException = new CpfException('CpfException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(cpfException)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'CpfException'

    }

    def 'InelegivelVotarException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        InelegivelVotarException exception = new InelegivelVotarException('InelegivelVotarException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'InelegivelVotarException'

    }

    def 'SessaoAbertaException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        SessaoAbertaException exception = new SessaoAbertaException('SessaoAbertaException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'SessaoAbertaException'

    }

    def 'SessaoInexistenteException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        SessaoInexistenteException exception = new SessaoInexistenteException('SessaoInexistenteException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'SessaoInexistenteException'

    }

    def 'PautaExistenteException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        PautaExistenteException exception = new PautaExistenteException('PautaExistenteException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'PautaExistenteException'

    }

    def 'VotoExistenteException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        VotoExistenteException exception = new VotoExistenteException('VotoExistenteException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleInternalServerError(exception)

        then:
        response.statusCode == HttpStatus.BAD_REQUEST
        response.body.mensagem == 'VotoExistenteException'

    }

    def 'NotFoundException lancada, controller deve enviar uma ErrorRespostaDto'() {
        given:

        NotFoundException exception = new NotFoundException('NotFoundException')

        when:
        ResponseEntity<ErrorRespostaDto> response = controllerHandler.handleNotFound(exception)

        then:
        response.statusCode == HttpStatus.NOT_FOUND
        response.body.mensagem == 'NotFoundException'

    }
}
