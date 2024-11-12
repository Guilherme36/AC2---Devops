
package ac2_project.example.ac2_ca.repository;

import ac2_project.example.ac2_ca.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
