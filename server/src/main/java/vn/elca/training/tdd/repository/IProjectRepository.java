package vn.elca.training.tdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.elca.training.tdd.dom.Project;
import vn.elca.training.tdd.repository.custom.project.IProjectRepositoryCustom;

import java.util.Optional;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long>, IProjectRepositoryCustom {
    
    Optional<Project> findByProjectNumber(Integer projectNumber);
    
}
