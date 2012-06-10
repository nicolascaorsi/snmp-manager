# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table icmp_echo_request_history (
  id                        bigint not null,
  ammount                   bigint,
  date                      timestamp,
  constraint pk_icmp_echo_request_history primary key (id))
;

create table memory_usage_history (
  id                        bigint not null,
  amount                    bigint,
  date                      timestamp,
  constraint pk_memory_usage_history primary key (id))
;

create table processes_history (
  id                        bigint not null,
  number_of_processes       bigint,
  date                      timestamp,
  constraint pk_processes_history primary key (id))
;

create table system_description_history (
  id                        bigint not null,
  description               varchar(255),
  date                      timestamp,
  constraint pk_system_description_history primary key (id))
;

create table uptime_history (
  id                        bigint not null,
  seconds                   bigint,
  date                      timestamp,
  constraint pk_uptime_history primary key (id))
;

create sequence icmp_echo_request_history_seq;

create sequence memory_usage_history_seq;

create sequence processes_history_seq;

create sequence system_description_history_seq;

create sequence uptime_history_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists icmp_echo_request_history;

drop table if exists memory_usage_history;

drop table if exists processes_history;

drop table if exists system_description_history;

drop table if exists uptime_history;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists icmp_echo_request_history_seq;

drop sequence if exists memory_usage_history_seq;

drop sequence if exists processes_history_seq;

drop sequence if exists system_description_history_seq;

drop sequence if exists uptime_history_seq;

