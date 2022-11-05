package ru.akirakozov.sd.refactoring.html;

import ru.akirakozov.sd.refactoring.data.Product;

import java.util.List;

public class HtmlUtils {
    private static String addHeaders(StringBuilder body, String header) {
        return "<html><body>\n" +
            header +
            body.toString() +
            "</body></html>";
    }

    public static String getStatisticHTML(Integer res, String header) {
        return addHeaders(new StringBuilder(res), header);
    }

    public static String getProductsHTML(List<Product> products, String header) {
        StringBuilder res = new StringBuilder();
        for (Product product: products) {
            res.append(addProduct(product));
        }
        return addHeaders(res, header);
    }

    private static StringBuilder addProduct(Product product) {
        return new StringBuilder(product.getName()).append("\t").append(product.getPrice()).append("</br>\n");
    }
}
