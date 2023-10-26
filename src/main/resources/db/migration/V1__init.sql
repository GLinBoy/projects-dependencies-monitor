CREATE TABLE project
(
	id         bigserial primary key,
	title      varchar(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	created_at timestamp with time zone default CURRENT_TIMESTAMP,
	updated_at timestamp with time zone
);
