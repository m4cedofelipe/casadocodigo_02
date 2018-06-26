package br.com.casadocodigo.loja.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {
	
	/*
	 * Tratamento de exceção generica
	 * A anotação @ControllerAdvice é uma especialização do @Component. 
	 * As classes anotadas com @ControllerAdvice são detectadas automaticamente pela varredura do caminho de classe. 
	 * O uso de @ControllerAdvice está aconselhando todos os controladores ou selecionados para @ExceptionHandler, @InitBinder e @ModelAttribute. 
	 * O que temos que fazer é criar uma classe anotada com @ControllerAdvice e criar o método requerido que será 
	 * anotado com @ExceptionHandler para manipulação de exceção global, @InitBinder para ligação init global e @ModelAttribute para adição de atributos de modelo global. 
	 * Sempre que uma solicitação chegar ao controlador e seu método com @RequestMapping e 
	 * se não houver @ExceptionHandler, @InitBinder e @ModelAttribute definidos localmente, a classe definida globalmente anotada com @ControllerAdvice será exibida. 
	 * @ControllerAdvice tem atributos anotações , assignableTypes e basePackages . 
	 * Para usar @ControllerAdvice, devemos usar a anotação @EnableWebMvc em nossa configuração java. 
	 * */
	
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExceptionGenerica(Exception exception) {
		System.out.println("Erro genérico acontecendo");
		exception.printStackTrace();

		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
