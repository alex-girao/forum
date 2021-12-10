# forum
Api que simula um forum de dúvidas.
(SpringBoot, JPA, H2 Database, Lombok, Carga Inicial automática)

URL base da Aplicação
 * Running on http://localhost:8080/

## Banco de dados
 * URL: http://localhost:8080/h2-console
 * JDBC URL: jdbc:h2:mem:forum
 * Usuário: admin
 * Senha: 123
 
 ## Funcionalidades
 * Crud de Tópicos
 
## Endpoints - Tópicos
#### Listar todos 
GET /topicos
```bash
http://localhost:8080/topicos/
```
#### Listar por Id
GET /topicos/:id
```bash
http://localhost:8080/topicos/1
```
#### Remove
DELETE /topicos/:id
```bash
http://localhost:8080/topicos/2
```
#### Create
POST /topicos
```bash
http://localhost:8080/topicos/
```
Request Body 
```bash
{
	"titulo": "Erro JavaScript",
	"mensagem": "Erro na importação de arquivo no HTML5",
	"nomeCurso": "HTML 5"
}
```
#### Update
PUT /topicos/:id
```bash
http://localhost:8080/topicos/2
```
Request Body 
```bash
{
  "titulo": "Erro no Build no Eclipse",
  "mensagem": "Projeto não compila no Eclipse"
}
```




