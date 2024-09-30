package org.akira.pedidos.controllers;

import org.akira.pedidos.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class OrderController {
    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping("/novo")
    public ResponseEntity<String> criarPedido(@RequestBody Order order) {
        jmsTemplate.convertAndSend("new-orders", order);
        return ResponseEntity.ok("Pedido criado com sucesso!");
    }
}
