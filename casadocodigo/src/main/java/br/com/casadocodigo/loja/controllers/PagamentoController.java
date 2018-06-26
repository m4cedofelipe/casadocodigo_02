package br.com.casadocodigo.loja.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.models.CarrinhoCompras;
import br.com.casadocodigo.loja.models.DadosPagamento;
import br.com.casadocodigo.loja.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	RestTemplate restTemplate;
	

	/*
	 * Inteface [ Callable ] - Interface para a implementação de uma execução em
	 * paralelo (processamento assincrono)
	 * 
	 */

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario ,RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";

			try {

				if (carrinho.getItens().isEmpty()) {
					return new ModelAndView("redirect:/carrinho"); // Se não houver itens no carrinho retorna para a tela
				}

				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
				model.addFlashAttribute("sucesso", response);

				System.out.println(response);

				enviaEmailCompraProduto(usuario);

				carrinho.clear(); // limpa a lista de carrinho

				return new ModelAndView("redirect:/produtos");

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("falha", "Valor maior que o permitido");
				return new ModelAndView("redirect:/produtos");
			}
		};
	}

	private void enviaEmailCompraProduto(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso !!");
		email.setFrom("compras@casadocodigo.com.br");
		//email.setTo(usuario.getEmail());
		email.setTo("steph4nie.oliveira@gmail.com");
		email.setText("Olá Compra aprovada com sucesso !!!<br><br> " 
				+" VALOR TOTAL: " + carrinho.getTotal());
		
		sender.send(email);
		
	}

}