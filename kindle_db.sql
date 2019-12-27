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
    variant character varying(255)
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
1	admin	admin@admin.com	admin
2	admin2	admin2@admin.com	admin3
\.


--
-- Data for Name: book; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.book (book_sku, title, author, year, description, price, merchant_id, document, merchant, variant) FROM stdin;
1	All The Light We Cannot See	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	1	/uploads/book-example.svg	\N	Black
2	All The Light We Cannot See 2	Anthony Doerr	2009	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.	50000	2	/uploads/book-example.svg	\N	Black
\.


--
-- Data for Name: bookcategory; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.bookcategory (book_sku, category_id) FROM stdin;
1	1
1	3
1	5
2	2
2	4
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
3	riandi2	riandi2@example.com	helloworld	Active
1	riandi	riandi@example.com	helloworld	Active
\.


--
-- Data for Name: library; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.library (customer_id, book_sku) FROM stdin;
1	1
1	2
\.


--
-- Data for Name: merchant; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.merchant (merchant_id, username, email, password, fullname, description, phone, status) FROM stdin;
1	gramedia	gramedia@example.com	helloworld	Gramedia	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	1234567890	Active
2	gramedia2	gramedia2@example.com	helloworld	Gramedia2	Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.	1234567899	Active
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (transaction_id, date, total, customer_id) FROM stdin;
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
\.


--
-- Data for Name: wishlist; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wishlist (customer_id, book_sku) FROM stdin;
1	1
3	2
1	2
\.


--
-- Name: admin_admin_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.admin_admin_id_seq', 4, true);


--
-- Name: book_book_sku_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.book_book_sku_seq', 2, true);


--
-- Name: category_category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_category_id_seq', 5, true);


--
-- Name: customer_customer_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customer_customer_id_seq', 3, true);


--
-- Name: merchant_merchant_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.merchant_merchant_id_seq', 2, true);


--
-- Name: transaction_list_transactionlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_list_transactionlist_id_seq', 1, false);


--
-- Name: transaction_transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_transaction_id_seq', 219, true);


--
-- Name: transactionlist_transactionlist_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transactionlist_transactionlist_id_seq', 171, true);


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
-- Name: library insert_library; Type: TRIGGER; Schema: public; Owner: postgres
--

CREATE TRIGGER insert_library BEFORE INSERT ON public.library FOR EACH ROW EXECUTE PROCEDURE public.delete_duplicated_value();


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
-- Name: cart cart_book_sku_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_book_sku_fkey FOREIGN KEY (book_sku) REFERENCES public.book(book_sku);


--
-- Name: cart cart_customer_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id);


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

