package com.salesianostriana.dam.casadobayonantoniojesus.repository;


import com.salesianostriana.dam.casadobayonantoniojesus.model.Taller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITallerRepository extends JpaRepository<Taller,Long> {
}
