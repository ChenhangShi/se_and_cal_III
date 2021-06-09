set names utf8mb4;

drop table if exists entity;
create table entity(
    id bigint not null,
    graphId bigint not null,
    name varchar(255) not null,
    type varchar(255) not null,
    shape varchar(255) not null,
    description varchar(255),
    x varchar(255) not null,
    y varchar(255) not null,
    primary key (id,graphId)
);

INSERT INTO `entity` VALUES (1,1,'source','t0','circle','test_source','123','123');
INSERT INTO `entity` VALUES (2,1,'target','t0','circle','test_target','124','125');


drop table if exists link;
create table link(
    id bigint not null,
    sourceId bigint not null,
    targetId bigint not null,
    graphId bigint not null,
    relationName varchar(255) not null,
    type varchar(255) not null,
    description varchar(255),
    isFullLine boolean not null ,
    primary key (id,graphId)
);

INSERT INTO `link` VALUES (1,1,2,1,'test','to','test',1);


drop table if exists user_tag_movie;
create table user_tag_movie(
    userId int not null,
    movie varchar(255) not null,
    primary key(userId,movie)
);

drop table if exists user_tag_actor;
create table user_tag_actor(
    userId int not null,
    actor varchar(255) not null,
    primary key(userId,actor)
);

drop table if exists user_tag_director;
create table user_tag_director(
    userId int not null,
    director varchar(255) not null,
    primary key(userId,director)
);

drop table if exists user_tag_genre;
create table user_tag_genre(
    userId int not null,
    genre varchar(255) not null,
    primary key(userId,genre)
);

drop table if exists director;
create table director(
  director_id int not null AUTO_INCREMENT,
  director_name varchar(255) not null,
  primary key(director_id)
);




drop table if exists graph;
create table graph(
    graphId bigint not null auto_increment,
    graphName varchar(255) not null,
    primary key (graphId)
);

INSERT INTO `graph` VALUES (1,'a');
