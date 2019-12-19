package com.kindle.backend.model.constant;

public class ApiPath {
  public static final String API = "/api";

  public static final String ADMIN = API + "/admins";
  public static final String ADMIN_BY_ADMIN_ID = ADMIN + "/{id}";
  public static final String ADMIN_LOGIN = ADMIN + "/login";

  public static final String BOOK = API + "/books";
  public static final String BOOK_BY_BOOK_SKU = BOOK + "/{sku}";

  public static final String CUSTOMER = API + "/customers";
  public static final String CUSTOMER_BY_CUSTOMER_ID = CUSTOMER + "/{customerId}";
  public static final String CUSTOMER_LOGIN = CUSTOMER + "/login";
  public static final String CUSTOMER_REGISTER = CUSTOMER + "/register";
  public static final String CUSTOMER_LIBRARY = CUSTOMER_BY_CUSTOMER_ID + "/library";

  public static final String MERCHANT = API + "/merchants";
  public static final String MERCHANT_BY_MERCHANT_ID = MERCHANT + "/{merchantId}";
  public static final String MERCHANT_LOGIN = MERCHANT + "/login";

  public static final String TRANSACTION = API + "/transactions";

  public static final String TRANSACTIONLIST = API + "/transactionlists";
}
