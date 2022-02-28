package empresas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import empresas.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long>{
	@Query(value = "select e from Empresa e where e.nome like %?1% ")
	List<Empresa> buscarPorNome(String nome);

}
