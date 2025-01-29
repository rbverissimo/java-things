package br.com.coltran.farmacinhapp.controllers.api;

import br.com.coltran.farmacinhapp.domain.Farmacia;
import br.com.coltran.farmacinhapp.domain.Remedio;
import br.com.coltran.farmacinhapp.services.FarmaciaService;
import br.com.coltran.farmacinhapp.services.RemedioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class RemediosApiControllerTest {

    private MockMvc mvc;

    @Mock
    private RemedioService remedioService;

    @Mock
    private FarmaciaService farmaciaService;

    @InjectMocks
    private RemediosApiController remediosApiController;

    private JacksonTester<Page<Remedio>> jsonPageRemedio;

    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(remediosApiController).build();
    }

    public void canRetrievePaginaByNome() throws Exception{

        Pageable pageable = Pageable.ofSize(12).withPage(0);
        long farmaciaId = 1L;

        Farmacia farmacia = new Farmacia();
        farmacia.setId(farmaciaId);
        farmacia.setNome("Farmácia Mock");

        List<Remedio> remedios = new ArrayList<>();

        Remedio dorflex500mg = new Remedio();
        dorflex500mg.setNome("Dorflex 500mg");
        dorflex500mg.setFarmacia(farmacia);
        dorflex500mg.setDoses(24);
        dorflex500mg.setConsumoDiario(1);
        dorflex500mg.setDataInicioTratamento(LocalDate.now().atStartOfDay(ZoneId.of("America_SaoPaulo")));


        Remedio dorflex1g = new Remedio();
        dorflex1g.setNome("Dorflex 1g");
        dorflex1g.setFarmacia(farmacia);
        dorflex1g.setDoses(24);
        dorflex1g.setConsumoDiario(1);
        dorflex1g.setDataInicioTratamento(LocalDate.now().atStartOfDay(ZoneId.of("America_SaoPaulo")));

        remedios.add(dorflex1g);
        remedios.add(dorflex500mg);

        Page<Remedio> pageImpl = new PageImpl<Remedio>(remedios, pageable, remedios.size());

        given(remedioService.getRemediosByNome(farmaciaId, "Dorflex", pageable))
                .willReturn(pageImpl);

        MockHttpServletResponse response = mvc.perform(get("/api/remedios/1")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonPageRemedio.write(pageImpl).getJson());
    }

}
