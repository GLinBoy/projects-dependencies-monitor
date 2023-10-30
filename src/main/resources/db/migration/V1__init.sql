CREATE TABLE project
(
	id BIGINT PRIMARY KEY,
	title      VARCHAR(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE dependency
(
	id         BIGINT PRIMARY KEY,
	title      VARCHAR(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE versions
(
	id            BIGINT PRIMARY KEY,
	title         VARCHAR(128),
-- 	created_by bigint        not null REFERENCES users (id),
-- 	updated_by bigint        not null REFERENCES users (id),
	dependency_id BIGINT,
	FOREIGN KEY (dependency_id) REFERENCES dependency (id),
	created_at    TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	updated_at    TIMESTAMP WITH TIME ZONE
);

CREATE TABLE ProjectDependency
(
	project_id BIGINT,
	version_id BIGINT,
	PRIMARY KEY (project_id, version_id),
	FOREIGN KEY (project_id) REFERENCES Project (id),
	FOREIGN KEY (version_id) REFERENCES versions (id)
);
