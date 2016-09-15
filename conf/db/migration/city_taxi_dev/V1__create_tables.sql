--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.0
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: map_blocks; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE map_blocks (
    id integer NOT NULL,
    "row" integer NOT NULL,
    col integer NOT NULL,
    map_id integer,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


--
-- Name: map_blocks_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE map_blocks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: map_blocks_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE map_blocks_id_seq OWNED BY map_blocks.id;


--
-- Name: maps; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE maps (
    id integer NOT NULL,
    name character varying NOT NULL,
    rows integer NOT NULL,
    cols integer NOT NULL,
    "time" integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


--
-- Name: maps_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE maps_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: maps_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE maps_id_seq OWNED BY maps.id;


--
-- Name: passengers; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE passengers (
    id integer NOT NULL,
    name character varying NOT NULL,
    map_id integer,
    taxi_id integer,
    "row" integer NOT NULL,
    col integer NOT NULL,
    dest_row integer NOT NULL,
    dest_col integer NOT NULL,
    status integer NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


--
-- Name: passengers_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE passengers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: passengers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE passengers_id_seq OWNED BY passengers.id;


--
-- Name: taxis; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE taxis (
    id integer NOT NULL,
    name character varying NOT NULL,
    status integer NOT NULL,
    "row" integer NOT NULL,
    col integer NOT NULL,
    map_id integer,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


--
-- Name: taxis_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE taxis_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: taxis_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE taxis_id_seq OWNED BY taxis.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY map_blocks ALTER COLUMN id SET DEFAULT nextval('map_blocks_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY maps ALTER COLUMN id SET DEFAULT nextval('maps_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY passengers ALTER COLUMN id SET DEFAULT nextval('passengers_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY taxis ALTER COLUMN id SET DEFAULT nextval('taxis_id_seq'::regclass);


--
-- Name: map_blocks_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY map_blocks
    ADD CONSTRAINT map_blocks_pkey PRIMARY KEY (id);


--
-- Name: maps_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY maps
    ADD CONSTRAINT maps_pkey PRIMARY KEY (id);


--
-- Name: passengers_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passengers
    ADD CONSTRAINT passengers_pkey PRIMARY KEY (id);


--
-- Name: taxis_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY taxis
    ADD CONSTRAINT taxis_pkey PRIMARY KEY (id);


--
-- Name: index_map_blocks_on_map_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX index_map_blocks_on_map_id ON map_blocks USING btree (map_id);


--
-- Name: index_passengers_on_map_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX index_passengers_on_map_id ON passengers USING btree (map_id);


--
-- Name: index_passengers_on_taxi_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX index_passengers_on_taxi_id ON passengers USING btree (taxi_id);


--
-- Name: index_taxis_on_map_id; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX index_taxis_on_map_id ON taxis USING btree (map_id);

--
-- Name: fk_rails_1b64ab55c7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passengers
    ADD CONSTRAINT fk_rails_1b64ab55c7 FOREIGN KEY (taxi_id) REFERENCES taxis(id);


--
-- Name: fk_rails_464156bdbd; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY taxis
    ADD CONSTRAINT fk_rails_464156bdbd FOREIGN KEY (map_id) REFERENCES maps(id);


--
-- Name: fk_rails_47fee86bf8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY passengers
    ADD CONSTRAINT fk_rails_47fee86bf8 FOREIGN KEY (map_id) REFERENCES maps(id);


--
-- Name: fk_rails_667c30121d; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY map_blocks
    ADD CONSTRAINT fk_rails_667c30121d FOREIGN KEY (map_id) REFERENCES maps(id);
