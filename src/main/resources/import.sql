insert into foro (nome) value ('Foro Regional XII - Nossa Senhora do Ó'), ('Fórum Ourinhos');

insert into competencia (nome) value ('Cível'), ('Família e Sucessões'), ('Criminal');

insert into foro_competencias (foro_id, competencia_id) values (1, 1), (1, 2), (1, 3), (2, 1);

insert into classe_processo (nome, competencia_id) values ('Usucapião', 1), ('Execução de Título Extrajudicial', 1), ('Petição Criminal', 3), ('Adoção c/c Destituição de Poder Familiar', 2);

insert into assunto_principal (nome, classe_id) values ('Usucapião Ordinária', 1), ('Usucapião Extraordinária', 1), ('Homicídio Doloso', 3);

insert into peticao_inicial_primeiro_grau (valor, assunto_id) values (10000, 1), (50000, 3);




insert into participacao (nome) value ('Confrontante'), ('Requerente'), ('Requerido'), ('Advogado');

insert into estado_civil (nome) value ('Solteiro'), ('Casado'), ('Separado judicialmente'), ('Divorciado'), ('Viúvo'), ('Companheiro'), ('Não identificado');

insert into profissao (nome) value ('Administrador'), ('Pedreiro'), ('Programador');

insert into nacionalidade (nome) value ('Alemão'), ('Brasileiro'), ('Canadense');

insert into parte (cep, complemento, cpf, genero, nome, numero_local, orgao_emissor, pessoa, rg, estado_civil_id, nacionalidade_id, participacao_id, profissao_id) values ('19900-000', 'Casa', '999.999.999-99', 'M', 'Carlos Silva', '203', 'SSP/SP', 'F', '33.333.333-3', 1, 2, 1, 1);
insert into parte (cep, complemento, cpf, genero, nome, numero_local, orgao_emissor, pessoa, rg, estado_civil_id, nacionalidade_id, participacao_id, profissao_id) values ('19900-000', 'Apartamento', '888.888.888-88', 'F', 'Maria Oliveira', '55', 'SSP/SP', 'F', '11.111.111-1', 2, 2, 3, 3);