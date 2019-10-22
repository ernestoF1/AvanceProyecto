package pe.edu.upn.ProyectoWeb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upn.ProyectoWeb.model.entity.Venta;
import pe.edu.upn.ProyectoWeb.model.repository.VentaRepository;
import pe.edu.upn.ProyectoWeb.service.VentaService;

@Service
public class VentaServiceImpl implements VentaService{

	@Autowired
	private VentaRepository ventaRepository;
	@Transactional(readOnly=true)
	@Override
	public List<Venta> findAll() throws Exception {
		// TODO Auto-generated method stub
		return ventaRepository.findAll();
	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Venta> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return ventaRepository.findById(id);
	}

	@Transactional
	@Override
	public Venta save(Venta entity) throws Exception {
		// TODO Auto-generated method stub
		return ventaRepository.save(entity);
	}

	@Transactional
	@Override
	public Venta update(Venta entity) throws Exception {
		// TODO Auto-generated method stub
		return ventaRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		ventaRepository.deleteById(id);
		
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		ventaRepository.deleteAll();
		
	}

}
