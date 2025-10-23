package com.salesianostriana.dam.casadobayonantoniojesus.repository;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IFacturaRepository extends JpaRepository<Factura,Long> {
}
