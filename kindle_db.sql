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

--
-- Name: delete_duplicated_value(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.delete_duplicated_value() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ begin delete from library where customer_id = new.customer_id and book_sku = new.book_sku; return new; end; $$;


ALTER FUNCTION public.delete_duplicated_value() OWNER TO postgres;

--
-- Name: delete_from_cart(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.delete_from_cart() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ begin delete from cart where customer_id = new.customer_id and book_sku = new.book_sku; return new; end; $$;


ALTER FUNCTION public.delete_from_cart() OWNER TO postgres;

--
-- Name: delete_from_wishlist(); Type: FUNCTION; Schema: public; Owner: postgres
--

CREATE FUNCTION public.delete_from_wishlist() RETURNS trigger
    LANGUAGE plpgsql
    AS $$ begin delete from wishlist where customer_id = new.customer_id and book_sku = new.book_sku; return new; end; $$;


ALTER FUNCTION public.delete_from_wishlist() OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: admin; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.admin (
    admin_id integer NOT NULL,
    username character varying(255),
    email character varying(255),
    password character varying(255)
);


ALTER TABLE public.admin OWNER TO postgres;

--
-- Name: admin_admin_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.admin_admin_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.admin_admin_id_seq OWNER TO postgres;

--
-- Name: admin_admin_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.admin_admin_id_seq OWNED BY public.admin.admin_id;


--
-- Name: book; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.book (
    book_sku integer NOT NULL,
    title character varying(255) NOT NULL,
    author character varying(255),
    year integer,
    description text,
    price double precision DEFAULT 0 NOT NULL,
    merchant_id integer NOT NULL,
    document character varying(255),
    merchant character varying(255),
    variant character varying(255),
    url character varying(255),
    categories character varying(255)
);


ALTER TABLE public.book OWNER TO postgres;

--
-- Name: book_book_sku_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.book_book_sku_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.book_book_sku_seq OWNER TO postgres;

--
-- Name: book_book_sku_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.book_book_sku_seq OWNED BY public.book.book_sku;


--
-- Name: cart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cart (
    customer_id integer NOT NULL,
    book_sku integer NOT NULL
);


ALTER TABLE public.cart OWNER TO postgres;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    category_id integer NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.category_category_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.category_category_id_seq OWNER TO postgres;

--
-- Name: category_category_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.category_category_id_seq OWNED BY public.category.category_id;


--
-- Name: customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer (
    customer_id integer NOT NULL,
    username character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    status character varying(255) DEFAULT 'Active'::character varying NOT NULL
);


ALTER TABLE public.customer OWNER TO postgres;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customer_customer_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customer_customer_id_seq OWNER TO postgres;

--
-- Name: customer_customer_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customer_customer_id_seq OWNED BY public.customer.customer_id;


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
    merchant_id integer NOT NULL,
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
-- Name: merchant_merchant_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.merchant_merchant_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.merchant_merchant_id_seq OWNER TO postgres;

--
-- Name: merchant_merchant_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.merchant_merchant_id_seq OWNED BY public.merchant.merchant_id;


--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    transaction_id integer NOT NULL,
    date timestamp without time zone DEFAULT now(),
    total double precision DEFAULT 0 NOT NULL,
    customer_id integer NOT NULL
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_list; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction_list (
    transactionlist_id integer NOT NULL,
    book_sku integer,
    merchant_id integer,
    transaction_id integer
);


ALTER TABLE public.transaction_list OWNER TO postgres;

--
-- Name: transaction_list_transactionlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_list_transactionlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_list_transactionlist_id_seq OWNER TO postgres;

--
-- Name: transaction_list_transactionlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_list_transactionlist_id_seq OWNED BY public.transaction_list.transactionlist_id;


--
-- Name: transaction_transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaction_transaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaction_transaction_id_seq OWNER TO postgres;

--
-- Name: transaction_transaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaction_transaction_id_seq OWNED BY public.transaction.transaction_id;


--
-- Name: transactionlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transactionlist (
    transactionlist_id integer NOT NULL,
    book_sku integer NOT NULL,
    merchant_id integer NOT NULL,
    transaction_id integer NOT NULL
);


ALTER TABLE public.transactionlist OWNER TO postgres;

--
-- Name: transactionlist_transactionlist_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transactionlist_transactionlist_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transactionlist_transactionlist_id_seq OWNER TO postgres;

--
-- Name: transactionlist_transactionlist_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transactionlist_transactionlist_id_seq OWNED BY public.transactionlist.transactionlist_id;


--
-- Name: wishlist; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wishlist (
    customer_id integer NOT NULL,
    book_sku integer NOT NULL
);


ALTER TABLE public.wishlist OWNER TO postgres;

--
-- Name: admin admin_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.admin ALTER COLUMN admin_id SET DEFAULT nextval('public.admin_admin_id_seq'::regclass);


--
-- Name: book book_sku; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book ALTER COLUMN book_sku SET DEFAULT nextval('public.book_book_sku_seq'::regclass);


--
-- Name: category category_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category ALTER COLUMN category_id SET DEFAULT nextval('public.category_category_id_seq'::regclass);


--
-- Name: customer customer_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customer ALTER COLUMN customer_id SET DEFAULT nextval('public.customer_customer_id_seq'::regclass);


--
-- Name: merchant merchant_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.merchant ALTER COLUMN merchant_id SET DEFAULT nextval('public.merchant_merchant_id_seq'::regclass);


--
-- Name: transaction transaction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction ALTER COLUMN transaction_id SET DEFAULT nextval('public.transaction_transaction_id_seq'::regclass);


--
-- Name: transaction_list transactionlist_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction_list ALTER COLUMN transactionlist_id SET DEFAULT nextval('public.transaction_list_transactionlist_id_seq'::regclass);


--
-- Name: transactionlist transactionlist_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transactionlist ALTER COLUMN transactionlist_id SET DEFAULT nextval('public.transactionlist_transactionlist_id_seq'::regclass);


--
-- Data for Name: admin; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.admin (admin_id, username, email, password) FROM stdin;
2	admin2	admin2@admin.com	admin3
1	admin5	admin@admin.com	admin
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (book_sku, title, author, year, description, price, merchant_id, document, merchant, variant, url, categories) FROM stdin;
4	All The Light We Cannot See3	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	1	/uploads/book-example.svg	\N	Black	/files/Book1.pdf	Fiction;Drama;Romance
1	All The Light We Cannot See	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	1	/uploads/book-example.svg	\N	Black	/files/Book1.pdf	Fiction;Drama;Romance
3	All The Light We Cannot See3	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	1	/uploads/book_image-1578048893949.svg	\N	Black	/files/book_file-1578048830503.pdf	Fiction;Drama;Romance
2	All The Light We Cannot See 2	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	2	/uploads/book-example.svg	\N	Black	/files/Book1.pdf	Non-fiction;Horror
\.


--
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cart (customer_id, book_sku) FROM stdin;
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (category_id, name) FROM stdin;
1	Fiction
2	Non-fiction
3	Drama
4	Horror
5	Romance
\.


--
-- Data for Name: customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.customer (customer_id, username, email, password, status) FROM stdin;
1	riandi123	riandi@example.com	helloworld	Active
6	user3	user3@example.com	helloworld	Inactive
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
1	gramedia	gramedia@example.com	helloworld	Gramedia	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	1234567890	Active
2	gramedia2	gramedia2@example.com	helloworld	Gramedia2	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	1234567899	Active
5	gramedia3	gramedia3@example.com	helloworld	Gramedia5	sadioasgfoasivfbouqwvfouasvfouasvfasuovdoas	134567890	Active
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (transaction_id, date, total, customer_id) FROM stdin;
223	2019-12-27 13:51:25.079138	100000	1
224	2019-12-27 14:59:35.824231	100000	1
225	2019-12-27 15:00:03.274806	50000	1
226	2019-12-27 15:02:30.020992	50000	1
227	2019-12-30 10:37:38.422045	50000	1
\.


--
-- Data for Name: transaction_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction_list (transactionlist_id, book_sku, merchant_id, transaction_id) FROM stdin;
\.


--
-- Data for Name: transactionlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transactionlist (transactionlist_id, book_sku, merchant_id, transaction_id) FROM stdin;
172	1	1	223
173	2	2	223
174	2	2	224
175	1	1	224
176	1	1	225
177	2	2	226
178	1	1	227
\.


--
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist (customer_id, book_sku) FROM stdin;
\.


--
-- Name: admin_admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_admin_id_seq', 4, true);


--
-- Name: book_book_sku_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_book_sku_seq', 5, true);


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_category_id_seq', 7, true);


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 7, true);


--
-- Name: merchant_merchant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchant_merchant_id_seq', 5, true);


--
-- Name: transaction_list_transactionlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_list_transactionlist_id_seq', 1, false);


--
-- Name: transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_transaction_id_seq', 227, true);


--
-- Name: transactionlist_transactionlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactionlist_transactionlist_id_seq', 178, true);


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
-- Name: transaction_list transaction_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction_list
    ADD CONSTRAINT transaction_list_pkey PRIMARY KEY (transactionlist_id);


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
-- Name: library delete_from_cart; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER delete_from_cart AFTER INSERT ON public.library FOR EACH ROW EXECUTE PROCEDURE public.delete_from_cart();


--
-- Name: library delete_from_wishlist; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER delete_from_wishlist AFTER INSERT ON public.library FOR EACH ROW EXECUTE PROCEDURE public.delete_from_wishlist();


--
-- Name: library insert_library; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_library BEFORE INSERT ON public.library FOR EACH ROW EXECUTE PROCEDURE public.delete_duplicated_value();


--
-- Name: book book_merchant_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.book
    ADD CONSTRAINT book_merchant_id_fkey FOREIGN KEY (merchant_id) REFERENCES public.merchant(merchant_id);


--
-- Name: library fk54gtsoqq0x2lrv5shywv46qjh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library
    ADD CONSTRAINT fk54gtsoqq0x2lrv5shywv46qjh FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: cart fk88o5eyf3qrsya0hd2xmdcjaf4; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fk88o5eyf3qrsya0hd2xmdcjaf4 FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: wishlist fkb6xak0rjui1rsok8ll7ln59cs; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT fkb6xak0rjui1rsok8ll7ln59cs FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: cart fkdebwvad6pp1ekiqy5jtixqbaj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT fkdebwvad6pp1ekiqy5jtixqbaj FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


--
-- Name: library fkgc9eh02trhkfmypan0h3d171v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.library
    ADD CONSTRAINT fkgc9eh02trhkfmypan0h3d171v FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: wishlist fkmxiw4elwsameupoyyqydg9khf; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wishlist
    ADD CONSTRAINT fkmxiw4elwsameupoyyqydg9khf FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


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
-- PostgreSQL database dump complete
--

