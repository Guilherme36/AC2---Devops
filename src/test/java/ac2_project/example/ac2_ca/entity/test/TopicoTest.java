package ac2_project.example.ac2_ca.entity.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import ac2_project.example.ac2_ca.entity.Topico;

public class TopicoTest {

    @Test
    void testSetAndGetAluno() {
        Topico topico = new Topico();
        topico.setAluno("Aluno1");

        assertEquals("Aluno1", topico.getAluno());
    }

    @Test
    void testSetAndGetDescricao() {
        Topico topico = new Topico();
        topico.setDescricao("Descrição do tópico de teste.");

        assertEquals("Descrição do tópico de teste.", topico.getDescricao());
    }

    @Test
    void testTopicoIdGeneration() {
        Topico topico = new Topico();
        topico.setId(1L);

        assertNotNull(topico.getId());
        assertEquals(1L, topico.getId());
    }
}