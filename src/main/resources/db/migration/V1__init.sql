CREATE TABLE project
(
	id BIGINT generated always as identity PRIMARY KEY,
	title      VARCHAR(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE dependency
(
	id         BIGINT generated always as identity PRIMARY KEY,
	title      VARCHAR(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE versions
(
	id            BIGINT generated always as identity PRIMARY KEY,
	version_number VARCHAR(64),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	dependency_id BIGINT,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (dependency_id) REFERENCES dependency (id)
);

CREATE TABLE project_dependency
(
	project_id BIGINT,
	version_id BIGINT,
	PRIMARY KEY (project_id, version_id),
	FOREIGN KEY (project_id) REFERENCES Project (id),
	FOREIGN KEY (version_id) REFERENCES versions (id)
);
