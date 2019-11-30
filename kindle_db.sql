--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6 (Ubuntu 11.6-1.pgdg19.04+1)
-- Dumped by pg_dump version 11.6 (Ubuntu 11.6-1.pgdg19.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    admin_id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_sku serial NOT NUL
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--L,
    title character varying(255) NOT NULL,
    author character varying(255),
    year integer,
    description text,
    document bytea,
    price double precision DEFAULT 0 NOT NULL,
    rating double precision DEFAULT 0,
    merchant_id integer NOT NULL
);


ALTER TABLE public.book OWNER TO postgres;

--
-- Name: bookcategory; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.bookcategory (
    book_sku integer NOT NULL,
    category_id integer NOT NULL
);


ALTER TABLE public.bookcategory OWNER TO postgres;

--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    cart_id serial NOT NULL,
    customer_id integer NOT NULL
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- Name: cartlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cartlist (
    cardlist_id serial NOT NULL,
    book_sku integer NOT NULL,
    cart_id integer NOT NULL
);


ALTER TABLE public.cartlist OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    category_id serial NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    status character varying(255) DEFAULT 'Active'::character varying NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: library; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.library (
    customer_id integer NOT NULL,
    book_sku integer NOT NULL
);


ALTER TABLE public.library OWNER TO postgres;

--
-- Name: merchant; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.merchant (
    merchant_id serial NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    fullname character varying(255),
    description text,
    phone character varying(255),
    status character varying(255) DEFAULT 'Active'::character varying NOT NULL
);


ALTER TABLE public.merchant OWNER TO postgres;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    transaction_id serial NOT NULL,
    date timestamp without time zone DEFAULT now() NOT NULL,
    total double precision DEFAULT 0 NOT NULL,
    customer_id integer NOT NULL
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transactionlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactionlist (
    transactionlist_id serial NOT NULL,
    book_sku integer NOT NULL,
    merchant_id integer NOT NULL,
    transaction_id integer NOT NULL
);


ALTER TABLE public.transactionlist OWNER TO postgres;

--
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    customer_id integer NOT NULL,
    book_sku integer NOT NULL
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin (admin_id, username, email, password) FROM stdin;
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (book_sku, title, author, year, description, document, price, rating, merchant_id) FROM stdin;
\.


--
-- Data for Name: bookcategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookcategory (book_sku, category_id) FROM stdin;
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (cart_id, customer_id) FROM stdin;
\.


--
-- Data for Name: cartlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cartlist (cardlist_id, book_sku, cart_id) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (category_id, name) FROM stdin;
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_id, username, email, password, status) FROM stdin;
\.


--
-- Data for Name: library; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.library (customer_id, book_sku) FROM stdin;
\.


--
-- Data for Name: merchant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchant (merchant_id, username, email, password, fullname, description, phone, status) FROM stdin;
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (transaction_id, date, total, customer_id) FROM stdin;
\.


--
-- Data for Name: transactionlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactionlist (transactionlist_id, book_sku, merchant_id, transaction_id) FROM stdin;
\.


--
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist (customer_id, book_sku) FROM stdin;
\.


--
-- Name: admin admin_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_email_key UNIQUE (email);


--
-- Name: admin admin_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_pkey PRIMARY KEY (admin_id);


--
-- Name: admin admin_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin
    ADD CONSTRAINT admin_username_key UNIQUE (username);


--
-- Name: book book_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_pkey PRIMARY KEY (book_sku);


--
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (cart_id);


--
-- Name: cartlist cartlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartlist
    ADD CONSTRAINT cartlist_pkey PRIMARY KEY (cardlist_id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (category_id);


--
-- Name: customer customer_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_email_key UNIQUE (email);


--
-- Name: customer customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);


--
-- Name: customer customer_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_username_key UNIQUE (username);


--
-- Name: merchant merchant_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_email_key UNIQUE (email);


--
-- Name: merchant merchant_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_pkey PRIMARY KEY (merchant_id);


--
-- Name: merchant merchant_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant
    ADD CONSTRAINT merchant_username_key UNIQUE (username);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (transaction_id);


--
-- Name: transactionlist transactionlist_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactionlist
    ADD CONSTRAINT transactionlist_pkey PRIMARY KEY (transactionlist_id);


--
-- Name: book book_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchant(merchant_id);


--
-- Name: bookcategory bookcategory_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookcategory
    ADD CONSTRAINT bookcategory_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: bookcategory bookcategory_category_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.bookcategory
    ADD CONSTRAINT bookcategory_category_id_fkey FOREIGN KEY (category_id) REFERENCES public.category(category_id);


--
-- Name: cart cart_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: cartlist cartlist_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartlist
    ADD CONSTRAINT cartlist_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: cartlist cartlist_cart_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cartlist
    ADD CONSTRAINT cartlist_cart_id_fkey FOREIGN KEY (cart_id) REFERENCES public.cart(cart_id);


--
-- Name: library library_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library
    ADD CONSTRAINT library_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: library library_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library
    ADD CONSTRAINT library_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: transaction transaction_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: transactionlist transactionlist_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactionlist
    ADD CONSTRAINT transactionlist_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: transactionlist transactionlist_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactionlist
    ADD CONSTRAINT transactionlist_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchant(merchant_id);


--
-- Name: transactionlist transactionlist_transaction_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactionlist
    ADD CONSTRAINT transactionlist_transaction_id_fkey FOREIGN KEY (transaction_id) REFERENCES public.transaction(transaction_id);


--
-- Name: wishlist wishlist_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: wishlist wishlist_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT wishlist_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- PostgreSQL database dump complete
--

