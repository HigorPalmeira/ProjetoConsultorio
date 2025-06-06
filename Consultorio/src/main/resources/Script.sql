/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  higor
 * Created: 1 de jun. de 2025
 */

drop database if exists consultoriodb;
create database consultoriodb;
use consultoriodb;

create table especialidade (
	id int primary key auto_increment,
	descricao varchar(100) unique not null
);

create table medico (
	id int primary key auto_increment,
	nome varchar(255) not null,
	crm varchar(20) unique not null,
	id_especialidade int,
	telefone varchar(20),
	email varchar(255),
	foreign key(id_especialidade) references especialidade(id)
);

create table paciente (
	id int primary key auto_increment,
	nome varchar(255) not null,
	cpf varchar(14) unique not null,
	data_nascimento date,
	telefone varchar(20),
	email varchar(255)
);

create table consulta (
	id int primary key auto_increment,
	id_medico int not null,
	id_paciente int not null,
	data_hora datetime not null,
	observacoes text,
	status enum('agendada', 'realizada', 'cancelada') default 'agendada',
	foreign key(id_medico) references medico(id),
	foreign key(id_paciente) references paciente(id)
);


create view medico_especialidade as
select m.id as id_medico,
		m.nome as nome_medico,
		m.crm as crm_medico,
		e.id as id_especialidade,
		e.descricao as descricao_especialidade,
		m.telefone as telefone_medico,
		m.email as email_medico
from medico m
inner join especialidade e on (e.id = m.id_especialidade);

create view consulta_paciente_medico as 
select c.id as id_consulta,
		m.id as id_medico,
		m.nome as nome_medico,
		m.crm as crm_medico,
		e.id as id_especialidade,
		e.descricao as descricao_especialidade,
		m.telefone as telefone_medico,
		m.email as email_medico,
		p.id as id_paciente,
		p.nome as nome_paciente,
		p.cpf as cpf_paciente,
		p.data_nascimento as data_nascimento_paciente,
		p.telefone as telefone_paciente,
		p.email as email_paciente,
		c.data_hora as data_hora_consulta,
		c.observacoes as observacoes_consulta,
		c.status as status_consulta
from consulta c 
inner join medico m on (m.id = c.id_medico)
inner join especialidade e on (e.id = m.id_especialidade)
inner join paciente p on (p.id = c.id_paciente);
