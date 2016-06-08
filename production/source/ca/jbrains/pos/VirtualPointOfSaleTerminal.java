package ca.jbrains.pos;

import java.util.HashMap;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        SaleController saleController = new SaleController(new InMemoryCatalog(new HashMap<String, Price>() {{
        }}), new EnglishLanguageConsoleDisplay());
    }
}
