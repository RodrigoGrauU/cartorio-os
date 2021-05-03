--criar tabela para entidade Cartorio
CREATE TABLE IF NOT EXISTS CARTORIO (
	id_cartorio SERIAL NOT NULL,
	nome VARCHAR(75) NOT NULL,
	endereco VARCHAR(75),
	
	PRIMARY KEY(id_cartorio)
);

--criar tabela para entidade Certidao
CREATE TABLE IF NOT EXISTS CERTIDAO (
	id_certidao SERIAL NOT NULL,
	id_cartorio INTEGER not null,
	nome VARCHAR(75) NOT NULL,
	
	PRIMARY KEY(id_certidao),
	FOREIGN KEY(id_cartorio) REFERENCES CARTORIO(id_cartorio)
);
