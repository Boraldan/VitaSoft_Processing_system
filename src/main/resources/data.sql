-- Вставка администраторов
INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Tom', 'tom@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32', TRUE, FALSE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Alis', 'alis@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',TRUE, FALSE, FALSE);

-- Вставка операторов
INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Sam', 'sam@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, TRUE, FALSE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Nina', 'nina@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, TRUE, FALSE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Leo', 'leo@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, TRUE, FALSE);

-- Вставка пользователей
INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('John', 'john@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, FALSE, TRUE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Anna', 'anna@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, FALSE, TRUE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Kate', 'kate@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, FALSE, TRUE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Mike', 'mike@x.com', '$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32', FALSE, FALSE, TRUE);

INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Sara', 'sara@x.com','$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, FALSE, TRUE);


INSERT INTO t_person (name, email, password, admin_role, operator_role, user_role)
VALUES ('Ara', 'Ara@x.com','$2a$10$VP0wDslO6cv953RLr2mg8OLCcvXh4LaD80CIag6cp2Pxh9DppUc32',FALSE, FALSE, TRUE);


INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('DRAFT', 'Request content for John in draft', NOW(), NOW(), 6);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('SENT', 'Request content for John sent', NOW(), NOW(), 6);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('ACCEPTED', 'Request content for John accepted', NOW(), NOW(), 6);
INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('DRAFT', 'Request content for Anna in draft', NOW(), NOW(), 7);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('SENT', 'Request content for Anna sent', NOW(), NOW(), 7);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('REJECTED', 'Request content for Anna rejected', NOW(), NOW(), 7);
INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('DRAFT', 'Request content for Kate in draft', NOW(), NOW(), 8);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('SENT', 'Request content for Kate sent', NOW(), NOW(), 8);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('ACCEPTED', 'Request content for Kate accepted', NOW(), NOW(), 8);
INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('DRAFT', 'Request content for Mike in draft', NOW(), NOW(), 9);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('SENT', 'Request content for Mike sent', NOW(), NOW(), 9);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('REJECTED', 'Request content for Mike rejected', NOW(), NOW(), 9);
INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('DRAFT', 'Request content for Sara in draft', NOW(), NOW(), 10);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('SENT', 'Request content for Sara sent', NOW(), NOW(), 10);

INSERT INTO t_request (status, content, created_date, updated_date, person_id)
VALUES ('ACCEPTED', 'Request content for Sara accepted', NOW(), NOW(), 10);