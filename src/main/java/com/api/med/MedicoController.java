package com.api.med;

import com.api.med.medico.DadosCadastroMedicoDTO;
import com.api.med.medico.Medico;
import com.api.med.medico.MedicoRepository;
import com.api.med.paciente.DadosCadastroPacienteDTO;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping("/medico")
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosMedico){
        medicoRepository.save(new Medico(dadosMedico));
    }

    @PostMapping("/paciente")
    public void cadastrarPaciente(@RequestBody DadosCadastroPacienteDTO dadosPaciente){
        System.out.println(dadosPaciente);
    }

}
