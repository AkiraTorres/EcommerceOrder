package org.akira.pagamentos.services;

import org.akira.pagamentos.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class PaymentListener {
    @Autowired
    private JmsTemplate jmsTemplate;

    @JmsListener(destination = "orders-confirmed")
    public void onOrderConfirmed(Order order) {
        if (processPayment(order)) {
            System.out.println("Pagamento do pedido " + order.getId() + " realizado com sucesso.");
            jmsTemplate.convertAndSend("payment-confirmed", order);
        } else {
            System.out.println("Pagamento do pedido " + order.getId() + " não foi realizado.");
        }
    }

    private boolean processPayment(Order order) {
        // Processa o pagamento do pedido (simulado)
        return true;
    }
}
