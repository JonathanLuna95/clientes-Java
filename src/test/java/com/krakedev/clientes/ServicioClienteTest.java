package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {

	@Test
	public void crearClienteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

		Cliente resultado = servicio.crearCliente(cliente);

		assertNotNull(resultado);
		assertEquals("1234567890", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
		assertEquals("juan@gmail.com", resultado.getEmail());
	}

	@Test
	public void crearClienteDuplicadoTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente1 = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

		Cliente cliente2 = new Cliente(
				"1234567890",
				"Carlos",
				"Lopez",
				"carlos@gmail.com"
		);

		servicio.crearCliente(cliente1);

		Cliente resultado = servicio.crearCliente(cliente2);

		assertNull(resultado);
	}

	@Test
	public void buscarPorCedulaTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

		servicio.crearCliente(cliente);

		Cliente resultado = servicio.buscarPorCedula("1234567890");

		assertNotNull(resultado);
		assertEquals("1234567890", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
		assertEquals("Perez", resultado.getApellido());
		assertEquals("juan@gmail.com", resultado.getEmail());
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

		Cliente cliente1 = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

		Cliente cliente2 = new Cliente(
				"0987654321",
				"Maria",
				"Lopez",
				"maria@gmail.com"
		);

		servicio.crearCliente(cliente1);
		servicio.crearCliente(cliente2);

		assertEquals(2, servicio.listar().size());
		assertEquals("juan@gmail.com", servicio.listar().get(0).getEmail());
		assertEquals("maria@gmail.com", servicio.listar().get(1).getEmail());
	}

	@Test
	public void actualizarClienteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente cliente = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

		servicio.crearCliente(cliente);

		Cliente clienteActualizado = new Cliente(
				"1234567890",
				"Carlos",
				"Lopez",
				"carlos@gmail.com"
		);

		Cliente resultado = servicio.actualizar(
				"1234567890",
				clienteActualizado
		);

		assertNotNull(resultado);
		assertEquals("Carlos", resultado.getNombre());
		assertEquals("Lopez", resultado.getApellido());
		assertEquals("carlos@gmail.com", resultado.getEmail());
	}

	@Test
	public void actualizarClienteNoExisteTest() {

		ServicioCliente servicio = new ServicioCliente();

		Cliente clienteActualizado = new Cliente(
				"9999999999",
				"Carlos",
				"Lopez",
				"carlos@gmail.com"
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

		Cliente cliente = new Cliente(
				"1234567890",
				"Juan",
				"Perez",
				"juan@gmail.com"
		);

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