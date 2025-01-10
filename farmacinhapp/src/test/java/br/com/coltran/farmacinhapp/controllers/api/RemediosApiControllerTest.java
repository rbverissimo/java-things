package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class RemediosApiControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    RemedioService remedioService;

    @MockBean
    FarmaciaService farmaciaService;

    @Test
    @WithMockUser
    public void index_nome_returnsRemedios() throws Exception{

        long farmaciaId = 2;
        String nome = "Dorf";

        Farmacia farmacia1 = new Farmacia();
        farmacia1.setNome("Farmácia 1");

        Farmacia farmacia2 = new Farmacia();
        farmacia1.setNome("Farmácia 2");

        Remedio remedio1 = new Remedio();
        remedio1.setNome("Dorflex");
        remedio1.setFarmacia(farmacia1);

        Remedio remedio2 = new Remedio();
        remedio2.setNome("Cataflan");
        remedio2.setFarmacia(farmacia2);

        Remedio remedio3 = new Remedio();
        remedio3.setNome("Novalgina");
        remedio3.setFarmacia(farmacia2);

        Remedio remedio4 = new Remedio();
        remedio4.setNome("Ibuprofeno");
        remedio4.setFarmacia(farmacia2);

        List<Remedio> remedios = List.of(remedio1, remedio2, remedio3, remedio4);
        Page<Remedio> remedioPage = new PageImpl<>(remedios, Pageable.ofSize(5).withPage(0), remedios.size());

        when(remedioService.getRemediosByNome(farmaciaId, nome, Pageable.ofSize(5).withPage(0))).thenReturn(remedioPage);
        when(farmaciaService.isResourceOwner(farmaciaId)).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/remedios/{farmacia_id}", farmaciaId)
                        .param("nome", nome)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
