package com.rodrigomargarido.springboot.entities.enums;

public enum OrderStatus {
	
	WAITING_PAYMENT(1), //Aguardando pagamento
	PAID(2), //Pago
	SHIPPED(3), //Enviado
	DELIVERED(4), //Entregue
	CANCELED(5); //Cancelado
	
	private int code;
	
	private OrderStatus(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	//Metodo para converter um tipo numérico em OrderStatus
	public static OrderStatus valueOf(int code) {
		for(OrderStatus value : OrderStatus.values() ) {
			if( value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
}
