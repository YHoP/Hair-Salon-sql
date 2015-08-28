--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: YHoP; Tablespace: 
--

CREATE TABLE clients (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying,
    stylist_id integer,
    service_id integer
);


ALTER TABLE clients OWNER TO "YHoP";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: YHoP
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "YHoP";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: YHoP
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: services; Type: TABLE; Schema: public; Owner: YHoP; Tablespace: 
--

CREATE TABLE services (
    id integer NOT NULL,
    service character varying
);


ALTER TABLE services OWNER TO "YHoP";

--
-- Name: services_id_seq; Type: SEQUENCE; Schema: public; Owner: YHoP
--

CREATE SEQUENCE services_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE services_id_seq OWNER TO "YHoP";

--
-- Name: services_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: YHoP
--

ALTER SEQUENCE services_id_seq OWNED BY services.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: YHoP; Tablespace: 
--

CREATE TABLE stylists (
    id integer NOT NULL,
    first_name character varying,
    last_name character varying
);


ALTER TABLE stylists OWNER TO "YHoP";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: YHoP
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "YHoP";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: YHoP
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: YHoP
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: YHoP
--

ALTER TABLE ONLY services ALTER COLUMN id SET DEFAULT nextval('services_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: YHoP
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: YHoP
--

COPY clients (id, first_name, last_name, stylist_id, service_id) FROM stdin;
1	Emily	Jones	1	1
2	Sophia	Williams	2	2
3	Olivia	Taylor	3	3
4	Charlie	Brown	1	4
5	Oscar	Davies	2	5
6	Harry	Wilson	3	6
7	Lily	Thomas	1	7
8	Ava	Johnson	2	8
9	Jessica	Robinson	3	1
10	Ethan	Thompson	1	2
11	Jacob	Walker	2	3
12	James	White	3	4
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: YHoP
--

SELECT pg_catalog.setval('clients_id_seq', 12, true);


--
-- Data for Name: services; Type: TABLE DATA; Schema: public; Owner: YHoP
--

COPY services (id, service) FROM stdin;
2	Designer Haircut & Style
3	Clipper Cut
4	Classic Blowout
5	Special Occasion Style
6	Wash & Blow-Dry
7	Color-Permanent
8	Color-Highlights
1	Classic Haircut
\.


--
-- Name: services_id_seq; Type: SEQUENCE SET; Schema: public; Owner: YHoP
--

SELECT pg_catalog.setval('services_id_seq', 8, true);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: YHoP
--

COPY stylists (id, first_name, last_name) FROM stdin;
1	Oliver	Smith
3	Alice	Edwards
4	Mia	Green
2	Ivy	Wood
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: YHoP
--

SELECT pg_catalog.setval('stylists_id_seq', 4, true);


--
-- Name: clients_pkey; Type: CONSTRAINT; Schema: public; Owner: YHoP; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: services_pkey; Type: CONSTRAINT; Schema: public; Owner: YHoP; Tablespace: 
--

ALTER TABLE ONLY services
    ADD CONSTRAINT services_pkey PRIMARY KEY (id);


--
-- Name: stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: YHoP; Tablespace: 
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: YHoP
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM "YHoP";
GRANT ALL ON SCHEMA public TO "YHoP";
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

