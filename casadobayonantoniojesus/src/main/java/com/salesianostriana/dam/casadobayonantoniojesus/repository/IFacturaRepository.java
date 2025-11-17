package com.salesianostriana.dam.casadobayonantoniojesus.repository;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import com.salesianostriana.dam.casadobayonantoniojesus.model.Factura;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface IFacturaRepository extends JpaRepository<Factura,Long> {



    public  List<Factura> findAllByCoche(Coche coche);
    public long countByCocheId(Long cocheId);


}
