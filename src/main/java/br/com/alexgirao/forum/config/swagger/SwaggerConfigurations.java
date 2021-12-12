package br.com.alexgirao.forum.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.alexgirao.forum.modelo.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Classe de configuracao do Swagger
 */
@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public Docket forumApi() {
		// documentacao do tipo SWAGGER_2
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				// pacote que sera a base para leitura de classes
				.apis(RequestHandlerSelectors.basePackage("br.com.alexgirao.forum"))
				// quais endoints devem ser analisados
				.paths(PathSelectors.ant("/**"))
				.build()
				// ignorar as URLs que trabaho com usuario
				.ignoredParameterTypes(Usuario.class)
				// adicionando um campo para envio do token
				.globalOperationParameters(Arrays.asList(
						new ParameterBuilder()
						.name("Authorization")
						.description("Header para token JWT")
						.modelRef(new ModelRef("string"))
						.parameterType("header")
						.required(false)
						.build()));
	}

}
