--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.4
-- Dumped by pg_dump version 9.5.4

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: test; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA test;


SET search_path = test;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: course; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE course (
    cid integer NOT NULL,
    name character varying(20)
);


--
-- Name: registration; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE registration (
    rid integer NOT NULL,
    sid integer,
    cid integer,
    grade integer
);


--
-- Name: student; Type: TABLE; Schema: test; Owner: -
--

CREATE TABLE student (
    sid integer NOT NULL,
    name character varying(20)
);


--
-- Data for Name: course; Type: TABLE DATA; Schema: test; Owner: -
--

INSERT INTO course VALUES (1, 'Math');
INSERT INTO course VALUES (2, 'Database');
INSERT INTO course VALUES (3, 'Java');
INSERT INTO course VALUES (4, 'Compiler');


--
-- Data for Name: registration; Type: TABLE DATA; Schema: test; Owner: -
--

INSERT INTO registration VALUES (1, 1, 1, 100);
INSERT INTO registration VALUES (2, 1, 2, 100);
INSERT INTO registration VALUES (3, 1, 3, 100);
INSERT INTO registration VALUES (4, 1, 4, 99);
INSERT INTO registration VALUES (5, 2, 1, 90);
INSERT INTO registration VALUES (6, 2, 2, 91);
INSERT INTO registration VALUES (7, 2, 3, 97);
INSERT INTO registration VALUES (8, 2, 4, 87);
INSERT INTO registration VALUES (9, 3, 1, 60);
INSERT INTO registration VALUES (10, 3, 2, 63);
INSERT INTO registration VALUES (11, 3, 3, 100);


--
-- Data for Name: student; Type: TABLE DATA; Schema: test; Owner: -
--

INSERT INTO student VALUES (1, 'Rugal');
INSERT INTO student VALUES (2, 'Adelheid');
INSERT INTO student VALUES (3, 'Orochi');


--
-- Name: course_pkey; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY course
    ADD CONSTRAINT course_pkey PRIMARY KEY (cid);


--
-- Name: registration_pkey; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY registration
    ADD CONSTRAINT registration_pkey PRIMARY KEY (rid);


--
-- Name: student_pkey; Type: CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY student
    ADD CONSTRAINT student_pkey PRIMARY KEY (sid);


--
-- Name: registration_cid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY registration
    ADD CONSTRAINT registration_cid_fkey FOREIGN KEY (cid) REFERENCES course(cid);


--
-- Name: registration_sid_fkey; Type: FK CONSTRAINT; Schema: test; Owner: -
--

ALTER TABLE ONLY registration
    ADD CONSTRAINT registration_sid_fkey FOREIGN KEY (sid) REFERENCES student(sid);


--
-- PostgreSQL database dump complete
--

