package pe.edu.upn.ProyectoWeb.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upn.ProyectoWeb.model.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}
