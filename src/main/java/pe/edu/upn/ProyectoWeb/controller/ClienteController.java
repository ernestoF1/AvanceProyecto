package pe.edu.upn.ProyectoWeb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upn.ProyectoWeb.model.entity.Cliente;
import pe.edu.upn.ProyectoWeb.model.entity.Producto;
import pe.edu.upn.ProyectoWeb.model.entity.TipoCliente;
import pe.edu.upn.ProyectoWeb.model.entity.TipoPago;
import pe.edu.upn.ProyectoWeb.model.entity.Venta;
import pe.edu.upn.ProyectoWeb.service.ClienteService;
import pe.edu.upn.ProyectoWeb.service.ProductoService;
import pe.edu.upn.ProyectoWeb.service.TipoClienteService;
import pe.edu.upn.ProyectoWeb.service.TipoPagoService;
import pe.edu.upn.ProyectoWeb.service.VentaService;

@Controller
@RequestMapping("/cliente")
@SessionAttributes({"cliente","venta"})
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private TipoClienteService tipoClienteService;
	
	@Autowired
	private TipoPagoService tipoPagoService;
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Cliente>clientes=clienteService.findAll();
			
			model.addAttribute("clientes", clientes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/cliente/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id")int id,Model model) {
		try {
			Optional<Cliente>optional=clienteService.findById(id);
			if(optional.isPresent()) {
				List<Venta>ventas=ventaService.findAll();
				
				model.addAttribute("cliente", optional.get());
				model.addAttribute("ventas",ventas);
			}
			else {
				return "redirect:/cliente";
			}
		} catch (Exception e) {
			
		}
		return "/cliente/edit";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("cliente")Cliente cliente, Model model,SessionStatus status) {
	     try {
			clienteService.save(cliente);
			status.setComplete();
		} catch (Exception e) {
			// TODO: handle exception
		}
	     return "redirect:/cliente";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Cliente cliente=new Cliente();
		model.addAttribute("cliente", cliente);
		
		
		try {
			List<TipoCliente>tipoClientes=tipoClienteService.findAll();
			model.addAttribute("tipoClientes", tipoClientes);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/cliente/nuevo";
	}
	
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id")int id, Model model) {
		try {
			Optional<Cliente>cliente=clienteService.findById(id);
			if(cliente.isPresent()) {
				clienteService.deleteById(id);
			}
		} catch (Exception e) {
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			 try {
				List<Cliente>clientes=clienteService.findAll();
				model.addAttribute("clientes", clientes);
			} catch (Exception e2) {
				// TODO: handle exception
			}
			 return "/cliente/inicio";
		}
		return "redirect:/cliente";
	}
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
			if(cliente.isPresent()) {
				model.addAttribute("cliente", cliente.get());
			} else {
				return "redirect:/cliente";
			}
		} catch (Exception e) {

		}	
		
		return "/cliente/info";
	}
	@GetMapping("/{id}/nuevaventa")
	public String nuevaVenta(@PathVariable("id") int id, Model model) {
		Venta venta = new Venta();
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
			if(cliente.isPresent()) {
				List<Producto>productos=productoService.findAll();
				List<TipoPago>tipoPagos=tipoPagoService.findAll();
				
				venta.setCliente(cliente.get());
				
				model.addAttribute("venta", venta);
				model.addAttribute("productos", productos);
				model.addAttribute("tipoPagos", tipoPagos);
			} else {
				return "redirect:/cliente";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/cliente/nuevaventa";
	}
	
	
	@PostMapping("/saveventa")
	public String saveVenta(@ModelAttribute("venta") Venta venta, 
			Model model, SessionStatus status) {
		try {
			ventaService.save(venta);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/cliente/info/" + venta.getCliente().getId();
	}
}
