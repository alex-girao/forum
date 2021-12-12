INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$E9mq3SB16Id8YLYWFxEmQuJ5IvXzUP7joWk3NC2aIGG27E3pbrshq');
INSERT INTO USUARIO(nome, email, senha) VALUES('Moderador', 'moderador@email.com', '$2a$10$E9mq3SB16Id8YLYWFxEmQuJ5IvXzUP7joWk3NC2aIGG27E3pbrshq');

INSERT INTO PERFIL(id, nome) VALUES(1,'ROLE_ALUNO');
INSERT INTO PERFIL(id, nome) VALUES(2,'ROLE_MODERADOR');

INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(1,1);
INSERT INTO USUARIO_PERFIS(usuario_id, perfis_id) VALUES(2,2);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Lib Lombok', 'Eclipse não reconhece as anotações', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Erro no Build', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('CSS 3', 'Como fazer animações em botões', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);
