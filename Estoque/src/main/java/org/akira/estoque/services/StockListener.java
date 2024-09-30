package org.akira.estoque.services;

import org.akira.estoque.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class StockListener {
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "new-orders")
    public void onNewOrder(Order order) {
        if (checkStock(order)) {
            System.out.println("Pedido " + order.getId() + " confirmado.");
            jmsTemplate.convertAndSend("orders-confirmed", order);
        } else {
            System.out.println("Pedido " + order.getId() + " não foi confirmado por falta de estoque.");
        }
    }

    private boolean checkStock(Order order) {
        // Verifica se há estoque suficiente para o pedido (simulado)
        return order.getQuantity() <= 50;
    }
}
