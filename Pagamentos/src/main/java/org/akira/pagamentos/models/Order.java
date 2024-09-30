package org.akira.pagamentos.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class Order implements Serializable {
    private Long id;
    private String description;
    private int quantity;
}
