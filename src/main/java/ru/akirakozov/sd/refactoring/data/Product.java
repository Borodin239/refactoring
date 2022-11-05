package ru.akirakozov.sd.refactoring.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String name;
    private int price;

    public String toString() {
        return String.format("(\"%s\", %d)", name, price);
    }
}
