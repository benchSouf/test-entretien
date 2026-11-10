// src/main/java/com/example/app/domain/Product.java
package com.example.app.domain;

public sealed interface Product permits Fx, Bond, Equity {
}