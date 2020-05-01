package com.andrelake.peticionamento.api.exceptionhandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.andrelake.peticionamento.domain.exception.EntidadeEmUsoException;
import com.andrelake.peticionamento.domain.exception.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
				
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		if(rootCause instanceof InvalidFormatException) {
			return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
		}
		else if(rootCause instanceof UnrecognizedPropertyException) {
			return handleUnrecognizedPropertyException((UnrecognizedPropertyException)rootCause, headers, status, request);
		}
		else if(rootCause instanceof IgnoredPropertyException) {
			return handleIgnoredPropertyException((IgnoredPropertyException)rootCause, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
				
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(ex instanceof MethodArgumentTypeMismatchException) {
			return handleMethodArgumentTypeMismatchException((MethodArgumentTypeMismatchException)ex, headers, status, request);
		}
		
		ProblemType problemType = ProblemType.URL_INVALIDA;
		String detail = "O endereço de url digitado é inválido.";
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		
		ProblemType problemType = ProblemType.ERRO_DE_SISTEMA;
		
		String detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, "
				+ "entre em contato com o administrador do sistema.";
		
		ex.printStackTrace();
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	//secondary methods
	private ResponseEntity<Object> handleIgnoredPropertyException(IgnoredPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.PROPRIEDADE_IGNORADA;
		String detail = String.format("A propriedade '%s' foi configurada para ser ignorada."
				+ " Corrija a requisição para um formato compatível.", path);
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleUnrecognizedPropertyException(UnrecognizedPropertyException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.PROPRIEDADE_INEXISTENTE;
		String detail = String.format("A propriedade '%s' não existe."
				+ " Corrija a requisição para um formato compatível.", path);
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String path = ex.getPath().stream()
				.map(ref -> ref.getFieldName())
				.collect(Collectors.joining("."));
		
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido."
				+ " Corrija e informe um valor compatível.", path, ex.getValue());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.URL_INVALIDA;
		String detail = String.format("O endereço URL '%s' recebeu o conteúdo '%s', que é inválido."
				+ " Corrija e informe um conteúdo compatível.", ex.getName(), ex.getValue());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O endereço URL '%s' não foi encontrado.", ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	//utils
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if(body == null) {
			body = Problem.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		}
		else if(body instanceof String) {
			body = Problem.builder()
					.title((String) body)
					.status(status.value())
					.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		
		return Problem.builder()
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail);
	}
}
