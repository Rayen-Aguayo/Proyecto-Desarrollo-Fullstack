package com.example.Facturacion.y.Presupuesto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Facturacion.y.Presupuesto.model.FacturacionYPresupuesto;

@Repository
public interface FacturacionYPresupuestoRepository extends JpaRepository<FacturacionYPresupuesto, Long>{

}
