package ac2_project.example.ac2_ca.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import ac2_project.example.ac2_ca.entity.Comentario;
import ac2_project.example.ac2_ca.repository.ComentarioRepository;

@ActiveProfiles("test")
@DataJpaTest
public class ComentarioRepositoryTest {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    void testSaveAndFindComentario() {
        // Cria um objeto Comentario com os valores necessários
        Comentario comentario = new Comentario();
        comentario.setAluno("Aluno1");
        comentario.setConteudo("Este é um comentário de teste.");

        // Salva no banco de dados
        Comentario savedComentario = comentarioRepository.save(comentario);
        assertNotNull(savedComentario.getId());  // Verifica se o ID foi gerado

        // Busca o comentário pelo ID
        Optional<Comentario> retrievedComentario = comentarioRepository.findById(savedComentario.getId());
        assertTrue(retrievedComentario.isPresent());
        assertEquals("Aluno1", retrievedComentario.get().getAluno());
        assertEquals("Este é um comentário de teste.", retrievedComentario.get().getConteudo());
    }
}
