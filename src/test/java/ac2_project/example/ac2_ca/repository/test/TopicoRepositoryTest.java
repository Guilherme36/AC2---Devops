package ac2_project.example.ac2_ca.repository.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import ac2_project.example.ac2_ca.entity.Topico;
import ac2_project.example.ac2_ca.repository.TopicoRepository;

@ActiveProfiles("test")
@DataJpaTest
public class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Test
    void testSaveAndFindTopico() {
        // Cria um objeto Topico com os valores necessários
        Topico topico = new Topico();
        topico.setAluno("Aluno1");
        topico.setDescricao("Descrição do tópico de teste.");

        // Salva no banco de dados
        Topico savedTopico = topicoRepository.save(topico);
        assertNotNull(savedTopico.getId());  // Verifica se o ID foi gerado

        // Busca o tópico pelo ID
        Optional<Topico> retrievedTopico = topicoRepository.findById(savedTopico.getId());
        assertTrue(retrievedTopico.isPresent());
        assertEquals("Aluno1", retrievedTopico.get().getAluno());
        assertEquals("Descrição do tópico de teste.", retrievedTopico.get().getDescricao());
    }
}
