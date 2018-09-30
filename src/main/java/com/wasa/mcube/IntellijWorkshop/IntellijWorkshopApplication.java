package com.wasa.mcube.IntellijWorkshop;

import com.wasa.mcube.IntellijWorkshop.service.VendingMachineTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntellijWorkshopApplication {

	private final VendingMachineTransaction vendingMachine;

	@Autowired
	public IntellijWorkshopApplication(VendingMachineTransaction vendingMachine) {
		this.vendingMachine = vendingMachine;
	}

	public static void main(String[] args) {
		SpringApplication.run(IntellijWorkshopApplication.class, args);
	}
}
