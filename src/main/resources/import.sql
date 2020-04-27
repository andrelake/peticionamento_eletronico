insert into foro (nome) value ('Foro Regional XII - Nossa Senhora do Ó'), ('Fórum Ourinhos');

insert into competencia (nome) value ('Cível'), ('Família e Sucessões'), ('Criminal');

insert into foro_competencias (foro_id, competencia_id) values (1, 1), (1, 2), (1, 3), (2, 1);

insert into classe_processo (nome, competencia_id) values ('Usucapião', 1), ('Execução de Título Extrajudicial', 1), ('Petição Criminal', 3), ('Adoção c/c Destituição de Poder Familiar', 2);

insert into assunto_principal (nome, classe_id) values ('Usucapião Ordinária', 1), ('Usucapião Extraordinária', 1), ('Homicídio Doloso', 3);

insert into peticao_inicial_primeiro_grau (valor, assunto_id) values (10000, 1), (50000, 3);