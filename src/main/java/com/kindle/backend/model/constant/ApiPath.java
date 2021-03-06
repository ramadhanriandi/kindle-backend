package com.kindle.backend.model.constant;

public class ApiPath {
  public static final String API = "/api";

  public static final String ADMIN = API + "/admins";
  public static final String ADMIN_BY_ADMIN_ID = ADMIN + "/{adminId}";
  public static final String ADMIN_LOGIN = ADMIN + "/login";

  public static final String BOOK = API + "/books";
  public static final String BOOK_BY_BOOK_SKU = BOOK + "/{sku}";
  public static final String BOOK_DETAIL = BOOK_BY_BOOK_SKU + "/detail";

  public static final String CATEGORY = API + "/categories";
  public static final String CATEGORY_BY_CATEGORY_ID = CATEGORY + "/{categoryId}";

  public static final String CUSTOMER = API + "/customers";
  public static final String CUSTOMER_BY_CUSTOMER_ID = CUSTOMER + "/{customerId}";
  public static final String CUSTOMER_LOGIN = CUSTOMER + "/login";
  public static final String CUSTOMER_CART = CUSTOMER_BY_CUSTOMER_ID + "/cart";
  public static final String CUSTOMER_CART_CHECK = CUSTOMER_CART + "/{bookSku}/check";
  public static final String CUSTOMER_LIBRARY = CUSTOMER_BY_CUSTOMER_ID + "/library";
  public static final String CUSTOMER_LIBRARY_CHECK = CUSTOMER_LIBRARY + "/{bookSku}/check";
  public static final String CUSTOMER_WISHLIST = CUSTOMER_BY_CUSTOMER_ID + "/wishlist";
  public static final String CUSTOMER_WISHLIST_CHECK = CUSTOMER_WISHLIST + "/{bookSku}/check";

  public static final String MERCHANT = API + "/merchants";
  public static final String MERCHANT_BY_MERCHANT_ID = MERCHANT + "/{merchantId}";
  public static final String MERCHANT_LOGIN = MERCHANT + "/login";
  public static final String MERCHANT_CATALOG = MERCHANT_BY_MERCHANT_ID + "/catalog";
  public static final String MERCHANT_ORDERS = MERCHANT_BY_MERCHANT_ID + "/orders";

  public static final String TRANSACTION = API + "/transactions";
  public static final String TRANSACTION_BY_TRANSACTION_ID = TRANSACTION + "/{transactionId}";

  public static final String TRANSACTIONLIST = API + "/transactionlists";
}
