/*
 * Autor: Higor Palmeira
 * https://github.com/HigorPalmeira
 * 
 * Script para o banco de dados do projeto de Sistema de Gerenciamento de Consultas (https://github.com/HigorPalmeira/ProjetoConsultorio)
 * Esse deverá ser o script final do banco de dados
 * 
*/

-- Banco de Dados
drop database if exists consultoriodb;
create database consultoriodb;

use consultoriodb;

-- Tabelas

create table endereco (
	id int primary key auto_increment,
	rua varchar(255) not null,
	numero varchar(10),
	bairro varchar(127) not null,
	cidade varchar(127) not null,
	estado varchar(2) not null, -- UF
	cep varchar(9) not null -- XXXXX-XXX
);

create table especialidade (
	id int primary key auto_increment,
	descricao varchar(255) unique not null
);

create table medico (
	id int primary key auto_increment,
	nome varchar(255) not null,
	crm varchar(20) unique not null,
	id_especialidade int,
	id_endereco int,
	status enum('ATIVO', 'INATIVO') default 'ATIVO',
	telefone varchar(20),
	email varchar(255) not null,
	
	constraint fk_medico_especialidade
		foreign key(id_especialidade) 
		references especialidade(id)
		on delete set null,
	
	constraint fk_medico_endereco
		foreign key(id_endereco) 
		references endereco(id)
		on delete set null
);

create table paciente (
	id int primary key auto_increment,
	nome varchar(255) not null,
	cpf varchar(14) unique not null, -- ###.###.###-##
	data_nascimento date,
	sexo enum('MASCULINO', 'FEMININO', 'NÃO DEFINIDO') default 'NÃO DEFINIDO',
	status enum('ATIVO', 'INATIVO') default 'ATIVO',
	telefone varchar(20),
	email varchar(255) not null,
	id_endereco int,
	
	constraint fk_paciente_endereco
		foreign key(id_endereco)
		references endereco(id)
		on delete set null
);

create table consulta (
	id int primary key auto_increment,
	id_medico int,
	id_paciente int,
	data_hora datetime not null,
	observacoes text,
	status enum('AGENDADA', 'CONFIRMADA', 'REALIZADA', 'CANCELADA', 'FALTOU') default 'AGENDADA',
	
	constraint fk_consulta_medico
		foreign key(id_medico)
		references medico(id)
		on delete set null,
		
	constraint fk_consulta_paciente
		foreign key(id_paciente)
		references paciente(id)
		on delete set null
);

-- Índices

create index idx_paciente_cpf_email -- login de usuários
on paciente(cpf, email);

create index idx_medico_crm_email -- login de usuários
on medico(crm, email);

-- Views

create view paciente_login as 
select email, cpf
from paciente;

create view medico_login as 
select email, crm
from medico;

create view paciente_detalhado as 
select p.id as id_paciente,
		p.nome as nome_paciente,
		p.cpf as cpf_paciente,
		p.data_nascimento as data_nascimento_paciente,
		p.sexo as sexo_paciente,
		p.status as status_paciente,
		p.telefone as telefone_paciente,
		p.email as email_paciente,
		e.id as id_endereco,
		e.rua as rua_endereco,
		e.numero as numero_endereco,
		e.bairro as bairro_endereco,
		e.cidade as cidade_endereco,
		e.estado as estado_endereco,
		e.cep as cep_endereco
from paciente p
left join endereco e on p.id_endereco = e.id;

create view medico_detalhado as
select m.id as id_medico,
		m.nome as nome_medico,
		m.crm as crm_medico,
		m.status as status_medico,
		m.telefone as telefone_medico,
		m.email as email_medico,
		esp.id as id_especialidade,
		esp.descricao as descricao_especialidade,
		edr.id as id_endereco,
		edr.rua as rua_endereco,
		edr.numero as numero_endereco,
		edr.bairro as bairro_endereco,
		edr.cidade as cidade_endereco,
		edr.estado as estado_endereco,
		edr.cep as cep_endereco
from medico m
left join especialidade esp on (m.id_especialidade = esp.id)
left join endereco edr on (m.id_endereco = edr.id);

create view consulta_detalhada as
select c.id as id_consulta,
		c.data_hora as data_hora_consulta,
		c.observacoes as observacoes_consulta,
		c.status as status_consulta,
		p.id as id_paciente,
		p.nome as nome_paciente,
		p.cpf as cpf_paciente,
		p.data_nascimento as data_nascimento_paciente,
		p.sexo as sexo_paciente,
		p.status as status_paciente,
		p.telefone as telefone_paciente,
		p.email as email_paciente,
		edr_p.id as id_endereco_paciente,
		edr_p.rua as rua_endereco_paciente,
		edr_p.numero as numero_endereco_paciente,
		edr_p.bairro as bairro_endereco_paciente,
		edr_p.cidade as cidade_endereco_paciente,
		edr_p.estado as estado_endereco_paciente,
		edr_p.cep as cep_endereco_paciente,
		m.id as id_medico,
		m.nome as nome_medico,
		m.crm as crm_medico,
		m.status as status_medico,
		m.telefone as telefone_medico,
		m.email as email_medico,
		esp.id as id_especialidade,
		esp.descricao as descricao_especialidade,
		edr_m.id as id_endereco_medico,
		edr_m.rua as rua_endereco_medico,
		edr_m.numero as numero_endereco_medico,
		edr_m.bairro as bairro_endereco_medico,
		edr_m.cidade as cidade_endereco_medico,
		edr_m.estado as estado_endereco_medico,
		edr_m.cep as cep_endereco_medico
from consulta c
left join paciente p on (c.id_paciente = p.id)
left join medico m on (c.id_medico = m.id)
left join especialidade esp on (m.id_especialidade = esp.id)
left join endereco edr_p on (p.id_endereco = edr_p.id)
left join endereco edr_m on (m.id_endereco = edr_m.id);
