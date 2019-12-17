package com.kindle.backend.model.constant;

public class ApiPath {
  public static final String API = "/api";

  public static final String ADMIN = API + "/admins";
  public static final String ADMIN_BY_ADMIN_ID = ADMIN + "/{id}";

  public static final String CUSTOMER = API + "/customers";
  public static final String CUSTOMER_BY_CUSTOMER_ID = CUSTOMER + "/{customerId}";
  public static final String CUSTOMER_LOGIN = CUSTOMER + "/login";
  public static final String CUSTOMER_REGISTER = CUSTOMER + "/register";

  public static final String MERCHANT = API + "/merchants";
  public static final String MERCHANT_BY_MERCHANT_ID = MERCHANT + "/{merchantId}";
  public static final String MERCHANT_LOGIN = MERCHANT + "/login";
}
