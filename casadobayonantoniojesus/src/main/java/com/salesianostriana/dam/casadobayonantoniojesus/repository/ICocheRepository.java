package com.salesianostriana.dam.casadobayonantoniojesus.repository;

import com.salesianostriana.dam.casadobayonantoniojesus.model.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ICocheRepository extends JpaRepository<Coche, Long> {
}
