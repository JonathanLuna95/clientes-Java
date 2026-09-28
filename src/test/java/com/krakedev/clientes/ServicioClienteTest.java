package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

@Service
public class ServicioClienteTest {

	@Test
	public void crearClienteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente("1234567890", "Juan", "Perez");

		Cliente resultado = servicio.crearCliente(cliente);

		assertNotNull(resultado);
		assertEquals("1234567890", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
	}

	@Test
	public void crearClienteDuplicadoTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente("1234567890", "Juan", "Perez");
		Cliente cliente2 = new Cliente("1234567890", "Carlos", "Lopez");

		servicio.crearCliente(cliente1);

		Cliente resultado = servicio.crearCliente(cliente2);

		assertNull(resultado);
	}

	@Test
	public void buscarPorCedulaTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente("1234567890", "Juan", "Perez");

		servicio.crearCliente(cliente);

		Cliente resultado = servicio.buscarPorCedula("1234567890");

		assertNotNull(resultado);
		assertEquals("1234567890", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
	}

	@Test
	public void buscarPorCedulaNoExisteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente resultado = servicio.buscarPorCedula("9999999999");

		assertNull(resultado);
	}

	@Test
	public void listarClientesTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente("1234567890", "Juan", "Perez");
		Cliente cliente2 = new Cliente("0987654321", "Maria", "Lopez");

		servicio.crearCliente(cliente1);
		servicio.crearCliente(cliente2);

		assertEquals(2, servicio.listar().size());
	}

	@Test
	public void actualizarClienteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente("1234567890", "Juan", "Perez");
		servicio.crearCliente(cliente);

		Cliente clienteActualizado = new Cliente(
				"1234567890",
				"Carlos",
				"Lopez"
		);

		Cliente resultado = servicio.actualizar(
				"1234567890",
				clienteActualizado
		);

		assertNotNull(resultado);
		assertEquals("Carlos", resultado.getNombre());
		assertEquals("Lopez", resultado.getApellido());
	}

	@Test
	public void actualizarClienteNoExisteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente clienteActualizado = new Cliente(
				"9999999999",
				"Carlos",
				"Lopez"
		);

		Cliente resultado = servicio.actualizar(
				"9999999999",
				clienteActualizado
		);

		assertNull(resultado);
	}

	@Test
	public void eliminarClienteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente("1234567890", "Juan", "Perez");

		servicio.crearCliente(cliente);

		boolean resultado = servicio.eliminar("1234567890");

		assertTrue(resultado);
		assertNull(servicio.buscarPorCedula("1234567890"));
	}

	@Test
	public void eliminarClienteNoExisteTest() {

		ServicioCliente servicio = new ServicioCliente();

		boolean resultado = servicio.eliminar("9999999999");

		assertFalse(resultado);
	}
}