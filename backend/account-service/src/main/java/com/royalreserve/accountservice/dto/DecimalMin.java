package com.royalreserve.accountservice.dto;

public @interface DecimalMin {

    String value();

    boolean inclusive();

    String message();

}
